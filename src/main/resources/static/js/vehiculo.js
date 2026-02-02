// Evento que pasa al abrir el archivo 
document.addEventListener("DOMContentLoaded", cargarVehiculos);


// Funcion para traer los vehiculos

async function cargarVehiculos() {
    const token = localStorage.getItem("token");

    try {
        const response = await fetch("http://localhost:8080/api/vehiculo", {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        const vehiculos = await response.json();


        tablaVehiculos(vehiculos);

    } catch (error) {
        console.error("Error en cargarVehiculos", error);
        alert("Erro al cargar los vehiculos de la base de datos");
    }

}

// Funcion para cargar la tabla de vehiculos

function tablaVehiculos(lista) {
    const tbody = document.getElementById("tablaVehiculos");

    if (!tbody) {
        console.error("No se encontro el tbody con id tablaVehiculos");
        return;
    }

    tbody.innerHTML = "";

    lista.forEach(v => {
        tbody.innerHTML +=
            `<tr>

                <td class="table-cell">
                    <div class="flex items-center gap-3">
                        <div
                            class="w-10 h-10 rounded-lg bg-blue-50 flex items-center justify-center text-primary">
                            <span class="material-symbols-outlined text-xl">commute</span>
                        </div>
                        <span class="font-semibold text-gray-900 dark:text-white">${v.modelo}</span>
                    </div>
                </td>

                <td class="table-cell font-mono text-xs text-[#617589]">${v.codigoIdent}</td>
                
                <td class="table-cell">
                    <span
                        class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-bold bg-green-100 text-green-700 uppercase">
                            ${v.estado
                ? `<span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-bold bg-green-100 text-green-700 uppercase">Disponible</span>`
                : `<span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-bold bg-red-100 text-red-700 uppercase">Inactivo</span>`
            }
                        </span>
                </td>

                <td class="table-cell text-right">
                    <a href="#" onclick="editar(${v.idVehiculo})">
                    <span class="material-symbols-outlined text-lg">edit</span>
                </a>
                
                <a href="#" onclick="eliminar(${v.idVehiculo})" class="ml-3">
                    <span class="material-symbols-outlined text-lg">delete</span>
                </a>
                </td>
</tr>
`
    });
}

// Funcion para desativar a los vehiculos
async function eliminar(id) {

    const token = localStorage.getItem("token");

    if (!confirm("Estas seguro de querer desactivar a este vehiculo ?")) {
        return;
    }


    try {

        const response = await fetch(`http://localhost:8080/api/vehiculo/delete/${id}`, {
            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {

            throw new Error("Sucedio un error al tratar de eliminar este vehiculo");
        }

        alert("Vehiculo eliminado/desactivado correctamente");
        cargarVehiculos();

    } catch (error) {
        console.error("Error enviado al eliminar completo", error);
        alert("Error al tratar de desactivar este vehiculo");
    }


}


// Funcion para editar
async function editar(id) {
    const token = localStorage.getItem("token");

    try {
        // 1. Obtener los datos del vehiculo
        const response = await fetch(`http://localhost:8080/api/vehiculo/${id}`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            throw new Error("Error al obtener vehiculo");
        }

        const vehiculo = await response.json();

        // 2. Rellenar un formulario o modal con los datos
        // Ejemplo: mostrar en un prompt (después podés hacer un modal)
        const nuevoModelo = prompt("Modelo:", vehiculo.modelo);
        const nuevoCodigo = prompt("Condigo Ident:", vehiculo.codigoIdent);
        const estadoNormal = vehiculo.estado;
        

        if (!nuevoModelo || !nuevoCodigo) {
            alert("Edición cancelada");
            return;
        }

        // 3. Enviar los datos actualizados
        const updateResponse = await fetch(`http://localhost:8080/api/vehiculo/edit/${id}`, {
            method: "PUT",
            headers: {
                "Authorization": "Bearer " + token,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                modelo: nuevoModelo,
                codigoIdent: nuevoCodigo,
                estado: estadoNormal
            })
        });

        if (!updateResponse.ok) {
            throw new Error("Error al actualizar vehiculo");
        }

        alert("Vehiculo actualizado correctamente");
        cargarVehiculos(); // Recargar la tabla

    } catch (error) {
        console.error(error);
        alert("Error al editar el vehiculo");
    }
}

// funcion para crear 
async function crearVehiculo(){
    const token = localStorage.getItem("token");

    // Pedir datos con prompts
    const newModelo = prompt("Modelo del auto:");
    const newCodigoIdent = prompt("Ingrese el codigo de identificaion");
    const estadoNormal = true;

    // Valido que no esten vacios 
    if (!newModelo || !newCodigoIdent ){
        alert("Todos los campos son obligatorios");
        return;
    }

    // Consumo de api 
    try {
        const response = await fetch("http://localhost:8080/api/vehiculo/save", {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + token,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                modelo: newModelo,
                codigoIdent: newCodigoIdent,
                estado: estadoNormal
            })
        });

        // Verificaion de promesa
        if(!response.ok){
            throw new Error("Error al crear el vehiculo");
        }
        // cambio de promesa a json
        const data = await response.json();
        console.log("Vehiculo creado:", data);

        alert("Vehiculo creado correctamente");
        cargarVehiculos(); // Recargar la tabla
        
    } catch (error) {
        console.error("Error completo:",error);
        alert("Sucedio un error al tratar de ingresar los datos del Vehiculo");
    }
}



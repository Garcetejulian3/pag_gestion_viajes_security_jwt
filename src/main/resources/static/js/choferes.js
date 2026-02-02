// Evento que pasa al abrir el archivo 
document.addEventListener("DOMContentLoaded", cargarChoferes);

// Funcion donde consume la api de traer todos los choferes
async function cargarChoferes() {
    const token = localStorage.getItem("token");

    try {
        const response = await fetch("http://localhost:8080/api/chofer", {
            headers: {
                "Authorization": "Bearer " + token
            }
        });


        const choferes = await response.json();


        tablaChoferes(choferes);

    } catch (error) {
        console.error("Error en cargarChoferes:", error);
        alert("Error al cargar los choferes de la base de datos");
    }
}

// Funcion para rellenar la tabla con los choferes obtenidos 
function tablaChoferes(lista) {
    const tbody = document.getElementById("tablaChoferes");


    if (!tbody) {
        console.error("No se encontró el elemento con id 'tablaChoferes'");
        return;
    }

    tbody.innerHTML = "";

    lista.forEach(c => {
        tbody.innerHTML += `
        <tr>
            <td class="table-cell">
                <div class="flex items-center gap-3">
                    <div class="w-8 h-8 rounded-full bg-blue-100 flex items-center justify-center text-primary font-bold text-xs">
                        ${c.nombre.charAt(0)}
                    </div>
                    <span class="font-semibold">
                        ${c.nombre} ${c.apellido}
                    </span>
                </div>
            </td>

            <td class="table-cell">
                ${c.numLicencia}
            </td>

            <td class="table-cell">
                    <span
                        class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-bold bg-green-100 text-green-700 uppercase">
                            ${c.activo
                ? `<span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-bold bg-green-100 text-green-700 uppercase">Disponible</span>`
                : `<span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-bold bg-red-100 text-red-700 uppercase">Inactivo</span>`
                            }
                        </span>
            </td>


            <td class="table-cell text-right">
                <a href="#" onclick="editar(${c.idChofer})">
                    <span class="material-symbols-outlined text-lg">edit</span>
                </a>
                
                <a href="#" onclick="eliminar(${c.idChofer})" class="ml-3">
                    <span class="material-symbols-outlined text-lg">delete</span>
                </a>
            </td>
        </tr>
        `;
    });
}

// Funcion para eliminar 
async function eliminar(id) {
    const token = localStorage.getItem("token");

    // Confirmo si quieren eliminar de verdad antes de hacerlo directamente
    if (!confirm("Estas seguro que quieres eliminar a este Chofer?")) {
        return;
    }

    try {

        const response = await fetch(`http://localhost:8080/api/chofer/eliminar/${id}`, {

            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + token
            }
        });



        if (!response.ok) {

            throw new Error("Sucedio un error al tratar de eliminar este chofer");
        }

        alert("Chofer eliminado/desactivado correctamente");
        cargarChoferes();


    } catch (error) {
        console.error("Error completo", error);
        alert("Error al eliminar este chofer");
    }

}

// Funcion para editar
async function editar(id) {
    const token = localStorage.getItem("token");

    try {
        // 1. Obtener los datos del chofer
        const response = await fetch(`http://localhost:8080/api/chofer/${id}`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            throw new Error("Error al obtener chofer");
        }

        const chofer = await response.json();

        // 2. Rellenar un formulario o modal con los datos
        // Ejemplo: mostrar en un prompt (después podés hacer un modal)
        const nuevoNombre = prompt("Nombre:", chofer.nombre);
        const nuevoApellido = prompt("Apellido:", chofer.apellido);
        const nuevaLicencia = prompt("Licencia:", chofer.numLicencia);

        if (!nuevoNombre || !nuevoApellido || !nuevaLicencia) {
            alert("Edición cancelada");
            return;
        }

        // 3. Enviar los datos actualizados
        const updateResponse = await fetch(`http://localhost:8080/api/chofer/editar/${id}`, {
            method: "PUT",
            headers: {
                "Authorization": "Bearer " + token,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                nombre: nuevoNombre,
                apellido: nuevoApellido,
                numLicencia: nuevaLicencia
            })
        });

        if (!updateResponse.ok) {
            throw new Error("Error al actualizar chofer");
        }

        alert("Chofer actualizado correctamente");
        cargarChoferes(); // Recargar la tabla

    } catch (error) {
        console.error(error);
        alert("Error al editar el chofer");
    }
}

// Funcion para guardar choferes
async function crearChofer() {
    const token = localStorage.getItem("token");

    // Pedir datos con prompts
    const nombre = prompt("Nombre del chofer:");
    const apellido = prompt("Apellido del chofer:");
    const numLicencia = prompt("Número de licencia:");

    // Validar que no estén vacíos
    if (!nombre || !apellido || !numLicencia) {
        alert("Todos los campos son obligatorios");
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/api/chofer/save", {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + token,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                nombre: nombre,
                apellido: apellido,
                numLicencia: numLicencia
            })
        });

        if (!response.ok) {
            throw new Error("Error al crear chofer");
        }

        const data = await response.json();
        console.log("Chofer creado:", data);

        alert("Chofer creado correctamente");
        cargarChoferes(); // Recargar la tabla

    } catch (error) {
        console.error(error);
        alert("Error al crear el chofer");
    }
}
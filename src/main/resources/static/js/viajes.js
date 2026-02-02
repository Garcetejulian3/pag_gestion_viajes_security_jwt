// Evento que pasa al abrir el archivo 
document.addEventListener("DOMContentLoaded", cargarViajes);

// Funcion para obtener los viajes
async function cargarViajes(){
    const token = localStorage.getItem("token");

    try {
        const response = await fetch("http://localhost:8080/api/viaje", {
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        console.log("Response status:", response.status);

        if (!response.ok) {
            throw new Error(`Error ${response.status}`);
        }

        const viajes = await response.json();
        console.log("Viajes recibidos:", viajes);

        tablaViajes(viajes);
        
    } catch (error) {
        console.error("Error completo:", error);
        alert("Error al traer los datos de los viajes: " + error.message);
    }
}

// Funcion para cargar la tabla de viajes
function tablaViajes(lista){
    const tbody = document.getElementById("tablaViajes");

    if (!tbody) {
        console.error("No se encontró el elemento con id 'tablaViajes'");
        return;
    }

    tbody.innerHTML = "";

    lista.forEach(v => {

        // Formatear la fecha
        const fecha = new Date(v.fechaSalida); // ← CORREGIDO
        const fechaFormateada = fecha.toLocaleDateString('es-AR', {
            year: 'numeric',
            month: 'short',
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });

        tbody.innerHTML += `
        <tr>
            <td class="table-cell">
                <div class="flex items-center gap-3">
                    <div class="w-10 h-10 rounded-lg bg-blue-50 flex items-center justify-center text-primary">
                        <span class="material-symbols-outlined text-xl">location_on</span>
                    </div>
                    <span class="font-semibold text-gray-900 dark:text-white">${v.destino}</span>
                </div>
            </td>

            <td class="table-cell text-[#617589]">${fechaFormateada}</td>

            <td class="table-cell">
                <div class="flex items-center gap-2">
                    <div class="w-7 h-7 rounded-full bg-gray-200 flex items-center justify-center text-[10px] font-bold">
                        ${v.nombreChofer ? v.nombreChofer.substring(0, 2).toUpperCase() : 'SC'}
                    </div>
                    <span class="text-gray-700 dark:text-gray-300">${v.nombreChofer || 'Sin chofer'}</span>
                </div>
            </td>

            <td class="table-cell font-medium text-gray-700 dark:text-gray-300">
                ${v.modeloVehiculo || 'Sin vehículo'}
            </td>

            <td class="table-cell text-right">
                <button onclick="editarViaje(${v.idViajes})" class="text-[#617589] hover:text-primary transition-colors">
                    <span class="material-symbols-outlined text-lg">edit</span>
                </button>
                <button onclick="eliminarViaje(${v.idViajes})" class="text-[#617589] hover:text-red-500 transition-colors ml-3">
                    <span class="material-symbols-outlined text-lg">delete</span>
                </button>
            </td>
        </tr>
        `;
    });
}

// Función para eliminar viaje
async function eliminarViaje(id) {
    const token = localStorage.getItem("token");

    if(!confirm("¿Estás seguro que quieres eliminar este viaje?")){
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/viaje/delete/${id}`, {
            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if(!response.ok){
            const errorText = await response.text();
            console.error("Error del servidor:", errorText);
            throw new Error("Error al eliminar viaje");
        }

        alert("Viaje eliminado correctamente");
        cargarViajes();

    } catch (error) {
        console.error("Error completo:", error);
        alert("Error al eliminar este viaje: " + error.message);
    }
}


// Función para abrir el modal y cargar choferes/vehículos
async function abrirModalCrearViaje() {
    const token = localStorage.getItem("token");
    
    try {
        // Cargar choferes activos
        const responseChoferes = await fetch("http://localhost:8080/api/chofer", {
            headers: { "Authorization": "Bearer " + token }
        });
        const choferes = await responseChoferes.json();
        //const choferesActivos = choferes.filter(c => c.activo);
        
        // Cargar vehículos
        const responseVehiculos = await fetch("http://localhost:8080/api/vehiculo", {
            headers: { "Authorization": "Bearer " + token }
        });
        const vehiculos = await responseVehiculos.json();
        
        // Llenar select de choferes
        const selectChofer = document.getElementById("nuevoChofer");
        selectChofer.innerHTML = '<option value="">-- Seleccionar chofer --</option>';
        choferes.forEach(c => {
            selectChofer.innerHTML += `
                <option value="${c.idChofer}">
                    ${c.nombre} ${c.apellido} - Lic: ${c.numLicencia}
                </option>
            `;
        });
        
        // Llenar select de vehículos
        const selectVehiculo = document.getElementById("nuevoVehiculo");
        selectVehiculo.innerHTML = '<option value="">-- Seleccionar vehículo --</option>';
        vehiculos.forEach(v => {
            selectVehiculo.innerHTML += `
                <option value="${v.idVehiculo}">
                    ${v.modelo} - ${v.patente}
                </option>
            `;
        });
        
        // Mostrar el modal
        document.getElementById("modalCrearViaje").classList.remove("hidden");
        
    } catch (error) {
        console.error("Error al cargar datos:", error);
        alert("Error al cargar choferes y vehículos");
    }
}

// Función para cerrar el modal de crear
function cerrarModalCrearViaje() {
    document.getElementById("modalCrearViaje").classList.add("hidden");
    
    // Limpiar campos
    document.getElementById("nuevoDestino").value = "";
    document.getElementById("nuevaFechaSalida").value = "";
    document.getElementById("nuevoChofer").value = "";
    document.getElementById("nuevoVehiculo").value = "";
}

// Función para guardar el viaje
async function guardarNuevoViaje() {
    const token = localStorage.getItem("token");
    
    // Obtener valores del formulario
    const destino = document.getElementById("nuevoDestino").value.trim();
    const fechaSalida = document.getElementById("nuevaFechaSalida").value;
    const idChofer = document.getElementById("nuevoChofer").value;
    const idVehiculo = document.getElementById("nuevoVehiculo").value;
    
    // Validar
    if (!destino || !fechaSalida || !idChofer || !idVehiculo) {
        alert("Todos los campos son obligatorios");
        return;
    }
    
    try {
        const response = await fetch("http://localhost:8080/api/viaje/save", {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + token,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                destino: destino,
                fechaSalida: fechaSalida,
                idChofer: parseInt(idChofer),
                idVehiculo: parseInt(idVehiculo)
            })
        });
        
        if (!response.ok) {
            const errorText = await response.text();
            console.error("Error del servidor:", errorText);
            throw new Error("Error al crear viaje");
        }
        
        const data = await response.json();
        console.log("Viaje creado:", data);
        
        alert("Viaje creado correctamente");
        cerrarModalCrearViaje();
        cargarViajes();
        
    } catch (error) {
        console.error(error);
        alert("Error al crear el viaje: " + error.message);
    }
}



// Función para abrir modal de editar y cargar datos
async function editarViaje(id) {
    const token = localStorage.getItem("token");
    
    try {
        // 1. Obtener datos del viaje
        const response = await fetch(`http://localhost:8080/api/viaje/${id}`, {
            headers: { "Authorization": "Bearer " + token }
        });
        
        if (!response.ok) {
            throw new Error("Error al obtener viaje");
        }
        
        const viaje = await response.json();
        
        // 2. Cargar choferes activos
        const responseChoferes = await fetch("http://localhost:8080/api/chofer", {
            headers: { "Authorization": "Bearer " + token }
        });
        const choferes = await responseChoferes.json();
        //const choferesActivos = choferes.filter(c => c.activo);
        
        // 3. Cargar vehículos
        const responseVehiculos = await fetch("http://localhost:8080/api/vehiculo", {
            headers: { "Authorization": "Bearer " + token }
        });
        const vehiculos = await responseVehiculos.json();
        
        // 4. Llenar campos del formulario de edición
        document.getElementById("editIdViaje").value = id;
        document.getElementById("editDestino").value = viaje.destino;
        
        // Formatear fecha para input datetime-local
        const fechaISO = new Date(viaje.fechaSalida).toISOString().slice(0, 16);
        document.getElementById("editFechaSalida").value = fechaISO;
        
        // 5. Llenar select de choferes
        const selectChofer = document.getElementById("editChofer");
        selectChofer.innerHTML = '<option value="">-- Seleccionar chofer --</option>';
        choferes.forEach(c => {
            const selected = c.idChofer === viaje.idChofer ? 'selected' : '';
            selectChofer.innerHTML += `
                <option value="${c.idChofer}" ${selected}>
                    ${c.nombre} ${c.apellido} - Lic: ${c.numLicencia}
                </option>
            `;
        });
        
        // 6. Llenar select de vehículos
        const selectVehiculo = document.getElementById("editVehiculo");
        selectVehiculo.innerHTML = '<option value="">-- Seleccionar vehículo --</option>';
        vehiculos.forEach(v => {
            const selected = v.idVehiculo === viaje.idVehiculo ? 'selected' : '';
            selectVehiculo.innerHTML += `
                <option value="${v.idVehiculo}" ${selected}>
                    ${v.modelo} 
                </option>
            `;
        });
        
        // 7. Mostrar el modal
        document.getElementById("modalEditarViaje").classList.remove("hidden");
        
    } catch (error) {
        console.error("Error al cargar datos del viaje:", error);
        alert("Error al cargar los datos del viaje");
    }
}

// Función para cerrar modal de editar
function cerrarModalEditarViaje() {
    document.getElementById("modalEditarViaje").classList.add("hidden");
}

// Función para guardar edición del viaje
async function guardarEdicionViaje() {
    const token = localStorage.getItem("token");
    
    // Obtener valores del formulario
    const id = document.getElementById("editIdViaje").value;
    const destino = document.getElementById("editDestino").value.trim();
    const fechaSalida = document.getElementById("editFechaSalida").value;
    const idChofer = document.getElementById("editChofer").value;
    const idVehiculo = document.getElementById("editVehiculo").value;
    
    // Validar
    if (!destino || !fechaSalida || !idChofer || !idVehiculo) {
        alert("Todos los campos son obligatorios");
        return;
    }
    
    try {
        const response = await fetch(`http://localhost:8080/api/viaje/edit/${id}`, {
            method: "PUT",
            headers: {
                "Authorization": "Bearer " + token,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                destino: destino,
                fechaSalida: fechaSalida,
                idChofer: parseInt(idChofer),
                idVehiculo: parseInt(idVehiculo)
            })
        });
        
        if (!response.ok) {
            const errorText = await response.text();
            console.error("Error del servidor:", errorText);
            throw new Error("Error al actualizar viaje");
        }
        
        alert("Viaje actualizado correctamente");
        cerrarModalEditarViaje();
        cargarViajes();
        
    } catch (error) {
        console.error(error);
        alert("Error al editar el viaje: " + error.message);
    }
}
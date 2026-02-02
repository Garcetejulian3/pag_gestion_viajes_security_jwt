async function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch("http://localhost:8080/auth/login",{
            method: "POST",
            headers:{
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username,
                password
            })
        });
        const data = await response.json();

        if(data.status){

            localStorage.setItem("token",data.jwt);
            localStorage.setItem("username",data.username);

            window.location.href= "http://127.0.0.1:5500/home.html";

        } else{
            alert("Login incorrecto");
        }



    } catch (error) {
        console.error("Error:",error);
        alert("Error al iniciar login y conectar al servidor de autenticacion")
    }
    
}
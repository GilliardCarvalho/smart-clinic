document.getElementById("loginForm").addEventListener("submit", async function (e) {
  e.preventDefault();

  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;
  const message = document.getElementById("message");

  try {
    const response = await fetch("http://localhost:8080/api/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });

    const result = await response.json();

    if (response.ok) {
      localStorage.setItem("token", result.token); // Salva o token
      message.style.color = "green";
      message.innerText = "Login bem-sucedido!";
      window.location.href = "/dashboard.html"; // Redireciona
    } else {
      message.style.color = "red";
      message.innerText = result.message || "Erro ao fazer login.";
    }
  } catch (error) {
    message.style.color = "red";
    message.innerText = "Erro de conexão com o servidor.";
  }
});

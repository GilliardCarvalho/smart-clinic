document.getElementById("loginForm").addEventListener("submit", async function (e) {
  e.preventDefault();

  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;
  const errorMessage = document.getElementById("errorMessage");

  try {
    const response = await fetch("http://localhost:8080/api/auth/login", { // 🔁 Rota corrigida!
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });

    const result = await response.json();

    if (response.ok) {
      localStorage.setItem("token", result.token);
      window.location.href = "/dashboard.html";
    } else {
      errorMessage.innerText = result.message || "Erro ao fazer login.";
    }
  } catch (error) {
    errorMessage.innerText = "Erro de conexão com o servidor.";
  }
});

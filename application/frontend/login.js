document.addEventListener("DOMContentLoaded", function () {
  document
    .getElementById("loginForm")
    .addEventListener("submit", function (event) {
      event.preventDefault();

      const username = document.getElementById("username").value;
      const password = document.getElementById("password").value;

      // Retrieve users from localStorage
      const users = JSON.parse(localStorage.getItem("users")) || [];

      // Validate credentials
      const user = users.find(
        (user) => user.username === username && user.password === password
      );

      if (user) {
        switch (user.role) {
          case "admin":
            window.location.href = "/admin"; // Redirect to Admin Dashboard
            break;
          case "staff":
            window.location.href = "/staff"; // Redirect to Staff Dashboard
            break;
          case "doctor":
            window.location.href = "/doctor"; // Redirect to Doctor Dashboard
            break;
          
        }

        
      } else {
        alert("Invalid username or password!");
      }
    });
});

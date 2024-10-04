document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    const signupForm = document.getElementById('signupForm');

    // Login Form Handling
    if (loginForm) {
        loginForm.addEventListener('submit', function(event) {
            event.preventDefault(); // Prevent the form from submitting

            const username = document.getElementById('username').value; // Changed to 'username'
            const password = document.getElementById('password').value; // Changed to 'password'

            // Clear previous error messages
            document.getElementById('loginUsernameError').textContent = '';
            document.getElementById('loginPasswordError').textContent = '';

            // Regular expression for username validation
            const usernameRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]+$/;

            // Check if username meets the criteria
            if (!usernameRegex.test(username)) {
                document.getElementById('loginUsernameError').textContent = 'Invalid Username!';
                return;
            }

            // Check password length (greater than or equal to 6)
            if (password.length < 6) {
                document.getElementById('loginPasswordError').textContent = 'Wrong Password!';
                return;
            }

            // Prepare data for submission
            const data = { username: username, password: password };

            // Send a POST request to the login endpoint
            fetch('/loginServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json' // Set content type to JSON
                },
                body: JSON.stringify(data) // Convert data to JSON string
            })
            .then(response => {
                if (response.ok) {
                    alert('Welcome to Library Management System')
                    // Redirect on successful login
                    window.location.href = 'http://localhost:8080/swagger-ui/index.html#/'; // Redirect on success
                }
                else {
                    return response.json().then(err => {
                        throw new Error(err.message || 'Login failed. Please check your credentials.');
                    });
                }
            })
            .catch(error => {
                document.getElementById('loginPasswordError').textContent = error.message;
            });
        });
    }

    // Signup Form Handling
    if (signupForm) {
        signupForm.addEventListener('submit', function(event) {
            event.preventDefault(); // Prevent the form from submitting

            const email = document.getElementById('email').value; // ID remains as 'email'
            const username = document.getElementById('username').value; // ID remains as 'username'
            const password = document.getElementById('password').value; // ID remains as 'password'

            // Clear previous error messages
            document.getElementById('signupEmailError').textContent = '';
            document.getElementById('signupUsernameError').textContent = '';
            document.getElementById('signupPasswordError').textContent = '';

            // Regular expression for email validation
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            // Regular expression for username validation
            const usernameRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]+$/;

            // Check if email meets the criteria
            if (!emailRegex.test(email)) {
                document.getElementById('signupEmailError').textContent = 'Please enter a valid email address.';
                return;
            }

            // Check if username meets the criteria
            if (!usernameRegex.test(username)) {
                document.getElementById('signupUsernameError').textContent = 'Username must contain at least one uppercase letter, one lowercase letter, one number, and one special character.';
                return;
            }

            // Check password length (greater than or equal to 6)
            if (password.length < 6) {
                document.getElementById('signupPasswordError').textContent = 'Password must be at least 6 characters long.';
                return;
            }

            // Prepare data for submission
            const signupData = { email: email, username: username, password: password };

            // Send a POST request to the signup endpoint
            fetch('/signupServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json' // Set content type to JSON
                },
                body: JSON.stringify(signupData) // Convert data to JSON string
            })
            .then(response => {
                if (response.ok) {
                    alert('Signup successful! Redirecting to login page...');
                    window.location.href = 'http://localhost:8080/lms'; // Redirect on success
                }
                else {
                    return response.json().then(err => {
                        throw new Error(err.message || 'Signup failed. Please try again.');
                    });
                }
            })
            .catch(error => {
                document.getElementById('signupPasswordError').textContent = error.message;
            });
        });
    }
});

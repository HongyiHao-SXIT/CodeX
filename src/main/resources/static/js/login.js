function login() {
    const loginAccount = document.getElementById('loginAccount').value;
    const password = document.getElementById('password').value;

    if (loginAccount === '') {
        alert('Login Account cannot be empty');
        document.getElementById('loginAccount').focus();
        return;
    }

    if (password === '') {
        alert('Password cannot be empty');
        document.getElementById('password').focus();
        return;
    }

    const databaseUsers = [
        { username: "user1", password: "password1" },
        { username: "user2", password: "password2" }
    ];

    let isRegistered = false;
    for (const user of databaseUsers) {
        if (user.username === loginAccount && user.password === password) {
            isRegistered = true;
            break;
        }
    }

    if (isRegistered) {
        alert('Login successful');
    } else {
        alert('Invalid username or password');
    }
}
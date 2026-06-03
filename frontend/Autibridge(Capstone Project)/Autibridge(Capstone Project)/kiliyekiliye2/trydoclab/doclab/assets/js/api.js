// AutiBridge API Helper
// Replaces Firebase SDK - all calls go to your Spring Boot backend

const API_BASE = 'https://autibridge-backend.onrender.com/api';

const AutiBridgeAPI = {

  async register(name, email, password) {
    const res = await fetch(`${API_BASE}/auth/register`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name, email, password })
    });
    const data = await res.json();
    if (!res.ok) throw new Error(data.error || 'Registration failed');
    return data;
  },

  async login(email, password) {
    const res = await fetch(`${API_BASE}/auth/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password })
    });
    const data = await res.json();
    if (!res.ok) throw new Error(data.error || 'Login failed');
    return data;
  },

  saveSession(authResponse) {
    localStorage.setItem('isLoggedIn', 'true');
    localStorage.setItem('token', authResponse.token);
    localStorage.setItem('username', authResponse.name);
    localStorage.setItem('email', authResponse.email);
  },

  clearSession() {
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('email');
  },

  isLoggedIn() {
    return localStorage.getItem('isLoggedIn') === 'true'
      && localStorage.getItem('token') !== null;
  },

  getToken() {
    return localStorage.getItem('token');
  },

  getUsername() {
    return localStorage.getItem('username');
  },

  async get(endpoint) {
    const res = await fetch(`${API_BASE}${endpoint}`, {
      headers: { 'Authorization': `Bearer ${this.getToken()}` }
    });
    if (res.status === 401) {
      this.clearSession();
      window.location.href = 'login.html';
      return;
    }
    const data = await res.json();
    if (!res.ok) throw new Error(data.error || 'Request failed');
    return data;
  },

  async post(endpoint, body) {
    const res = await fetch(`${API_BASE}${endpoint}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.getToken()}`
      },
      body: JSON.stringify(body)
    });
    if (res.status === 401) {
      this.clearSession();
      window.location.href = 'login.html';
      return;
    }
    const data = await res.json();
    if (!res.ok) throw new Error(data.error || 'Request failed');
    return data;
  }
};

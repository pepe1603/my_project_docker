import { createRouter, createWebHistory } from 'vue-router';
import Agenda from '../views/Agenda.vue';
import Home from '../views/Home.vue'
import About from '../views/AboutMe.vue';
import Categorias from '../views/Categorias.vue';
const routes = [
    {
    path: '/',
    name: 'Home',
    component: Home
    },
    {
    path: '/agenda',
    name: 'Agenda',
    component: Agenda
    },
    {
    path: '/categorias',
    name: 'Categorias',
    component: Categorias
    },
    {
    path: '/about',
    name: 'About',
    component: About
    },

  // Puedes añadir más rutas aquí para funcionalidades adicionales
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;

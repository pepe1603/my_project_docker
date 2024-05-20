<template>
        <nav class="nav-menu">
        <div class="leyend">
          <h2>Mi Agenda</h2>
          <span class="fas fa-book">
        </span>
        </div> 

        
        <ul class="links">
          <li :class="{ 'active ': $route.path =='/' }" ><router-link to="/">Home</router-link></li>
          <li :class="{ 'active ': $route.path =='/agenda' }"><router-link to="/agenda">Contactos</router-link></li>
          <li :class="{ 'active ': $route.path =='/categorias' }"><router-link to="/categorias">Categorias</router-link> </li>
          <li :class="{ 'active ': $route.path =='/about' }"><router-link to="/about">About Us</router-link> </li>

        </ul>
        <span id="btn-bars" class="fas fa-bars" @click="openDropdownMenu"></span>
        <div class="dropdown-links" :class=" { ' show ': isDropDownVisible } ">
          <span id="btn-xmark" class="fas fa-times" @click="closeDropdownMenu"></span>
          <ul class="links-dropdown">
            <li :class="{ 'activete ': isActiveLink('/') }" ><router-link to="/">Home</router-link></li>
          <li :class="{ 'activete ': isActiveLink('/agenda') }"><router-link to="/agenda">Contactos</router-link></li>
          <li :class="{ 'activete ': isActiveLink('/categorias') }"><router-link to="/categorias">Categorias</router-link> </li>
          <li :class="{ 'activete ': isActiveLink('/about') }"><router-link to="/about">About Us</router-link> </li>


          </ul> 
        </div>
      </nav>
</template>

<script>
export default {
    name: 'appHeader',
    data(){
      return{
        isDropDownVisible : false, //estado del menu desplegable
      }
    },
    computed:{
      isActiveLink(){
        return (route) => {
          return this.$route.path === route;
        }
      }
    },

    methods:{
      openDropdownMenu(){
        this.isDropDownVisible = !this.isDropDownVisible; //alternamos el esdo del menu desplegablke
      },
      closeDropdownMenu(){
        this.isDropDownVisible = false; //crrrar el menu desplegable
      }
    }
    

}
</script>

<style>
.nav-menu{
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 0 1rem;
  font-family: 'Prompt', sans-serif;
}

.leyend{
  display: flex;
  gap: 0.63em;
  align-items: center;
  text-align: center;
  font-family: 'Inconsolata','Open Sans',sans-serif;
  
}
.leyend h2{
  font-weight: 900;
}
.leyend h2:hover{
  text-shadow: 0px 4px 6px rgba(0, 0, 0, 0.8); /* Agregar sombra en la parte inferior */
}

.links{
  display: flex;
  gap: 2rem;
  justify-content: space-between;
  align-items: center;
}

.links li a {
  color: var(--cDark);
  text-decoration: none;
  padding: 5px 12px;
  border-radius: 10px;
  font-weight: 400;
  letter-spacing: 1px;
  transform: calc(1);
  transition: background-color .7s ease,
              transform .4s ease;
}

.links li.active a,
.links li a:hover {
  background-color: var(--cDark); /* Cambiar el color de fondo al hacer hover */
  color: var(--cLight); /* Cambiar el color del texto al hacer hover */
  transform: scale(1.2);
}

.dropdown-links{
  display: none;
}

</style>
<template>
  <div class="box-container">
    <h1>Categorias</h1>

    <p>Aqui encontraras las categorias disponibles.</p>

    <section class="box-categorias">
      <span @click="togglecategorias">Ver categorias <span :class="verCategorias ? 'fas fa-eye-slash icon-toggle-hidden' : 'fas fa-eye icon-toggle-hidden'" ></span></span> <!--cambiar a clase fas fa-eye-slash para ocultar--->
      <ul v-if="verCategorias" class="listaCategorias">
        <li v-if="listaCategorias.length === 0" class="message"><p>Nothing to see</p></li>
        <li v-for="categoria in listaCategorias" :key="categoria.id_categoria"
        > {{ categoria.nombre }}
        </li>
      </ul>
      <span class="numContactos">
        Total Categorias:  {{ listaCategorias.length }}      </span>


    </section>

    <article>
      <h3>Espera la nueva Actualizacion</h3>
      <p>Traerá una nueva funcion, Categorias Personalizadas.</p>
      <br>
      <br>
      <h2>Muy Pronto!!</h2>
    </article>
    
  </div>
</template>

<script>
import categoriaService from '../services/categoriaService';

export default {
    name: 'Categorias',
    data(){
      return{
        verCategorias: false,
        listaCategorias: [],

      }
    },

    mounted() {
      this.fetchCategorias();
    },
    
    methods: {
      fetchCategorias() {
        categoriaService.getAllCategorias()
        .then(reponse => {
          this.listaCategorias = reponse;

        })
        .catch(error => {
          console.error("Error al obtener las categorias: ",error);
        });
      },
      togglecategorias() {
        this.verCategorias = !this.verCategorias;
      }
    }

}
</script>

<style>
.box-categorias{
  padding: 1rem;
}

.box-categorias,
.listaCategorias{
    width: 90%;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  box-shadow: 0 0 6px;
  border-radius: 10px;
}
.listaCategorias li{
  width: 100%;
  height: 35px;
  display: flex;
  gap: .5em;
  align-items: center;
  border-radius: 10px;
  letter-spacing: 1px;
  padding-left: 1rem;
}
.listaCategorias li:hover{
  text-decoration: underline;
  
}
</style>
<template>
<div class="box-container">
    <div class="controls-menu">
      <div class="title">
        <h1>Mi-NoteBoock-Address</h1>
        <span class="numContactos">Total Contactos: {{ totalContactos }} </span>
      </div> 

      <button class="btn-refresh" @click="reloadData">Reload <span class="fas fa-arrows-rotate icon-refresh"></span></button>

    </div>
      <ul class="lista-contactos">
        <li v-if="totalContactos === 0" class="message"><p>Lo sentimos, no hay datos para mostrar <span>&#128542;</span></p> </li>
        <li v-for="contacto in listaContactos.value" 
            :key="contacto.id_contacto"
            >
            <section>
              <span class="fas fa-user"></span>
              <p>{{ contacto.nombre }}</p>
            </section>
            
            <span @click="verMasInfo(contacto.id_contacto)" class="fa fa-circle-info icon-info"></span>
        </li>
      </ul>

    <!-- Implementar modal para mas información del contacto -->
    <div id="modal-mas-info" class="modal micromodal-slide" aria-hidden="true">
      <div class="modal__overlay" tabindex="-1">
        <div class="modal__container" role="dialog" aria-modal="true" aria-labelledby="modal-mas-info-title">
          <header class="modal__header">
            <h2 class="modal__title" id="modal-mas-info-title">Más Información del Contacto</h2>
            <button class="modal__close" aria-label="Cerrar modal" data-micromodal-close>&times;</button>
          </header>
          <div class="modal__content" id="modal-mas-info-content">
            <!-- Aquí se mostrarán los datos del usuario -->
            <div class="avatar"><span class="fas fa-user icon-avatar"></span></div>
            <p><strong><i class="fa-solid fa-address-card"></i> : </strong> {{ contactoSeleccionado.nombre }}</p>
            <p><strong><i class="fa-solid fa-phone"></i> : </strong> {{ contactoSeleccionado.telefono }}</p>
            <p><strong><i class="fa-solid fa-envelope"></i> : </strong> {{ contactoSeleccionado.email }}</p>
            <p><strong><i class="fa-solid fa-address-book"></i> : </strong> {{ contactoSeleccionado.direccion }}</p>
            <p><strong><i class="fa-solid fa-people-group"></i> : </strong> {{ contactoSeleccionado.categoria}}</p>
            <!-- Agrega más campos según tus datos -->
          </div>
        </div>
      </div>
    </div><!-- fin Modal --->

    <!-- Sección para registrar un nuevo contacto -->
    <section class="btn-desplegable">
      <label>crear contacto</label>
      <button @click="mostrarFormularioNuevoContacto" class="btn-agregar"><span class="fas fa-circle-plus icon-btn-add"></span></button>
    </section> 
    <!-- Formulario para registrar un nuevo contacto -->
    <div class="registro-contacto" v-if="mostrarFormNuevoContacto">
      <h2>Datos del Contacto</h2><br>
      <form @submit.prevent="registrarNuevoContacto">

        

        <div class="form-input">
          <label for="nombre">Nombre</label>
          <input name="nombre" type="text" v-model="nuevoContacto.nombre" placeholder="my-name">
        </div>

        <div class="form-input">
          <label for="telefono">Telefono</label>
          <input name="telefono" type="text" v-model="nuevoContacto.telefono" placeholder="000-000-00-00">
        </div>

        <div class="form-input">
          <label for="correo">E-mail</label>
          <input name="correo" type="email" v-model="nuevoContacto.email" placeholder="my-email@example.com">
        </div>

        <div class="form-input">
          <label for="direccion">Direccion</label>
          <input name="direccion" type="text" v-model="nuevoContacto.direccion" placeholder="my-address">
        </div>

        <div class="form-input">
          <label for="categoria: ">Categoria: </label>
          <select name="categoria" id="categoria" v-model.number="nuevoContacto.categoria" >
            <option disabled value="" >Selecciona una categoria</option>
            <option v-for="categoria in listaCategorias" 
                    :key="categoria.id_categoria"
                    :value="categoria.id_categoria"
                    > <!-- Utiliza el ID de la categoría como valor -->
              {{ categoria.nombre }}
            </option>
          </select>
          
        </div>
        
        <div class="form-input">
          <button class="btn-guardar" type="submit"><span class="fas fa-save"></span> Save</button>
        </div>
        <button class="btn-cerrar" @click="cerrarFormularioNuevoContacto">&times;</button>
      </form>
    </div><!-- fin section registrar -->
    <!-- section eliminar Contacto por id--->

<br>
  <!-- Sección para eliminar un contacto por nombre -->
    <div class="form-input section-eliminar">
      <label for="txtEliminar">Delete Contact: </label>
      <input type="text" name="txtEliminar" id="txtEliminar" v-model="textoBusqueda" placeholder="Enter name to delete">
      
      <ul v-if="textoBusqueda" class="listaCoincidencias">
        <li v-if="contactosFiltrados.length === 0" class="message"><p>No hay considencias :[</p></li>
        <li  v-for="contacto in contactosFiltrados" :key="contacto.id_contacto">
          {{ contacto.nombre }} : {{ contacto.telefono }}
          <span class="fas fa-trash icon-trash" @click="eliminarContacto(contacto.id_contacto)"></span>
        </li>
      </ul>
    </div>

  </div>
</template>

<script>
import contactoService from '../services/contactoService';
import categoriaService from '../services/categoriaService';
import MicroModal from 'micromodal';
import { ref } from 'vue';

export default {
    name: 'Agenda',

    data(){
      return{
        listaContactos : ref([]),
        contactoSeleccionado: {
          nombre: '',
          telefono: '',
          email: '',
          direccion: '',
          categoria: ''
        }, // Para almacenar los datos del usuario seleccionado
        mostrarFormNuevoContacto: false,
        nuevoContacto: {
          nombre: '',
          telefono: '',
          email: '',
          direccion: '',
          categoria: '' //aqui se guardar ala categoria seleccionada
        },
        listaCategorias: [], //aqui se almacenara las categorias disponible
        totalContactos: 0,
        contactosFiltrados: [],
        textoBusqueda: ''


      }
    },
    mounted(){
      //al cargar el componete, obetendremos todas las categorias disponibles
      this.fetchCategorias();
      this.fetchContactos();
      MicroModal.init();
    },

    methods:{
      fetchCategorias() {
        categoriaService.getAllCategorias()
        .then(response => {
          console.log("respuesta: ",response);
          this.listaCategorias = response;
        })
        .catch(error => {
          console.log("Error al obtener las categorias: ",error);
        })
      },

      fetchContactos(){
        contactoService.getAllContactos()
        .then( contactos => {
          this.listaContactos.value = contactos;
          this.totalContactos = contactos.length;
        })
        .catch(error => {
          console.error("Error al Obtener Los Contactos: ", error);
        });
      },

      reloadData(){
          this.fetchContactos();
      },

      verMasInfo(idContacto) {
        // Cargar los datos del usuario utilizando el idContacto
        contactoService.getContactoById(idContacto)
        .then( contacto => {
          this.contactoSeleccionado = contacto;
          this.contactoSeleccionado.categoria = contacto.categoria.nombre;
          this.contactoSeleccionado.email = contacto.email;
        }).catch(error => {
          console.error("Error al opbtener La informacion de lsuaurio con id [ ",idContacto);
        })

        MicroModal.show('modal-mas-info');
      },
      mostrarFormularioNuevoContacto() {
        this.mostrarFormNuevoContacto = true;
      },
      cerrarFormularioNuevoContacto() {
        this.mostrarFormNuevoContacto = false;
      },

      registrarNuevoContacto() {

        console.log("nuevo contacto a guardar: ", this.nuevoContacto);
        //extraer id y nombre de catyegroia seleccionada
        const {id_categoria, nombre} = this.listaCategorias.find(categoria => 
                                                            categoria.id_categoria === this.nuevoContacto.categoria);

        //crear objeto categoria con id y nombre
        const categoria = {id_categoria, nombre};
        //crear objeto contacto con la categoria incluida
        

        const nuevoContactoConCategoria = {
          ...this.nuevoContacto, categoria,
          email: this.nuevoContacto.email//agregar el correo al objeto
        };



        console.log("ID de categoría seleccionado:",this.nuevoContacto, nuevoContactoConCategoria);
        contactoService.saveContacto(nuevoContactoConCategoria)
        .then(response => {
          console.log("Contacto Guardado: ", response.data);
            //limpiar campos         
          this.nuevoContacto.nombre = '';
          this.nuevoContacto.telefono = '';
          this.nuevoContacto.email = '';
          this.nuevoContacto.direccion = '';
          this.nuevoContacto.categoria = '';
          this.mostrarFormNuevoContacto = false;
          this.fetchContactos();

          alert('Contacto Guardado Exitosamente !!!')

      })
        .catch(error => {
          console.error("Error al registrar nuevo contacto: ", error);
        });
    },

    eliminarContacto(idContacto) {
      contactoService.deleteContacto(idContacto)
      .then( () => {
          //eliminar el contacto con la lista locakl
          console.log("Contacto eliminado correctamente del servidor");
          this.listaContactos.value = this.listaContactos.value.filter(item => item.idContacto !== idContacto); 
          
      })
      .catch(error => {
        console.error("Error al Eliminar el Contacto: ",error);
      })
      this.textoBusqueda = '';
          document.getElementById('txtEliminar').value='';
          alert('El Contacto ha sido Elimnado Exitosamente !!');
          alert("Si, los cambios no se ven reflejados haz Clik en boton [Reload]");
                    //actualizar el contador de total de contactos
          this.totalContactos = this.listaContactos.value.length;
          this.fetchContactos();
    },
    
    filtrarContactos() {
      this.contactosFiltrados = this.listaContactos.value.filter(contacto => {
        return contacto.nombre.toLowerCase().includes(this.textoBusqueda.toLowerCase());
      });
    },
  },
  watch: {
    textoBusqueda() {
      this.filtrarContactos();
    },
    /*
    listaContactos() {
      this.filtrarContactos();
    },*/

    }


}
</script>

<style>

.message{
  color: var(--success);
  letter-spacing: 1px;
  margin: .33rem;
}

.message p span{
  font-size: 1.2em;
}

.section-eliminar{
  margin: 1rem 0;
}


.btn-refresh{
  border: none;
  background-color: var(--cLink);
  color: var(--cLight);
  font-size: 16px;
  padding: 8px 12px;
  border-radius: 10px;
  margin-top: 1rem;
}

.btn-refresh .icon-refresh{
  animation: icon-rotate360 5s infinite;
  margin-left: .33rem;
}

.btn-refresh:hover{
  transform: scale(1.1);
}

@keyframes icon-rotate360 {
  to{
    transform: rotate(0deg);
  }
  from{
    transform: rotate(360deg);
  }
  
}
.numContactos{
  letter-spacing: 1px;
  font-family: 'Prompt', sans-serif;
  font-weight: 400;
  color: var(--cInfo);
}

.lista-contactos{
  width: 90%;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  padding: 1rem;
  box-shadow: 0 0 6px;
  border-radius: 15px;
}
.lista-contactos li{
  width: 100%;
  height: 40px;
  display: flex;
  gap: .5em;
  padding: .33rem .53rem;
  align-items: center;
  justify-content: space-between;
  border-radius: 10px;
  
}
.lista-contactos li section{
  display: flex;
  align-items: center;
  gap: 1rem;
}

.lista-contactos li section span{
  font-size: 1.5em;
}

.lista-contactos li section p:hover{
  text-decoration: underline;
  color: var(--cLink);
}

.lista-contactos li:hover .icon-info{
    padding-left: 1rem;
  border-left: thin solid var(--transparentDark);
}

.icon-info:hover{
  transition:  all .4s ease;
  color: var(--cLink);

}
.lista-contactos li:hover{
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); /* Agregar sombra en la parte inferior */
}

.listaCoincidencias{
  width: 100%;
  gap: .5em;
  padding: .33rem .53rem;
  border-radius: 10px;
  z-index: 999;
}
.listaCoincidencias li{
  width: 100%;
  height: 40px;
  display: flex;
  gap: .5em;
  padding: .33rem .53rem;
  align-items: center;
  justify-content: space-between;
  border-radius: 10px;
  transition: box-shadow .2s ease;
  letter-spacing: 1px;
}
.icon-trash{
  font-size: 18px;
}

.listaCoincidencias li:hover{
  box-shadow: 0 2px 6px var(--cLink);
  text-decoration: underline;
}
.listaCoincidencias li:hover .icon-trash{
  color: var(--cLink);
  animation: spin 1.2s infinite;
}

@keyframes spin {
  to{
    transform:  scale(1);
    transition: transform .7s ease;
  }
  from{
    transform: scale(1.2);
  }
  
}

/* micromodal.css */
.modal {
  display: none;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  padding: 1rem;
  overflow: auto;
  z-index: 1000;
}

.modal.is-open {
  display: block;
}

.modal__overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  padding: 1rem;
}

.modal__container {
  position: relative;
  margin: 4em auto;
  padding: 20px;
  background: #fff;
  border-radius: 5px;
  max-width: 465px;
}

.modal__header {
  border-bottom: 1px solid #ccc;
  padding-bottom: 10px;
  margin-bottom: 10px;
}

.modal__title {
  margin: 0;
}

.modal__close {
  position: absolute;
  top: 10px;
  right: 10px;
  height: 20px;
  width: 20px;
  border: none;
  text-align: center;
  border-radius: 50%;
  font-size: 18px;
  cursor: pointer;
  color: var(--cLink);

  transition: transform .2s ease;
}
.modal__close:hover {
  transform: scale(1.3);
  background-color: var(--cLink);
  color: var(--cLight);

}
.modal__content {
  padding-bottom: 20px;
  background: #ccc;
  display: flex;
  flex-direction: column;
  gap: .78rem;
  padding: 1rem;
  border-radius: 8px;
}

.modal__content .avatar{
  width: 100%;
  display: flex;
  justify-content: center;
}

.modal__content .avatar .icon-avatar{
  color: var(--cLight);
  padding: .5em;
  font-size: 1.5rem;
  background: var(--cLink);
  border-radius: 50%;
}
.modal__content p{
  display: flex;
  gap: 1rem;
}

/* Estilos para el formulario de registro */
.btn-desplegable{
  display: flex;
  gap: 1.3rem;
}


.btn-desplegable button{
  border: none;
  background: transparent;
}
.icon-btn-add{
  border: none;
  font-size: 1.5rem;
  color: var(--cLink);
  transition: transform .4s ease;
  border-radius: 50%;
  outline: 1px solid var(--transparentLight);
}
.btn-desplegable:hover .icon-btn-add{
  transform: scale(1.2);

}
.registro-contacto {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  
  border-radius: 10px;
  padding: 2rem;
}

.registro-contacto form {
  position: relative;
  background: var(--cLight);
  max-width: 450px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 5px;
  padding: 1.5em;
  box-shadow: 0 0 6px var(--cDark);
  gap: .75rem;
}

.form-input{
  width: 90%;
  font-family: 'Roboto',sans-serif;
  font-size: 16px;
  font-weight: 400;
  display: block;
}
.form-input:first-child{
  margin-top: 1.87rem;
}
.form-input label{
  line-height: 2px;
}

.form-input input{
  height: 32px;
  width: 100%;
  border: none;
  outline: thin solid var(--transparentDark);
  border-radius: 3px;
  margin-top: .33em;
  padding: 5px;
  letter-spacing: 1px;
}

.btn-guardar{
  padding: 8px 12px;
  display: inline-block;
  border: none;
  background: var(--cLink);
  color: var(--cLight);
  border-radius: 5px ;
  font-weight: bold;
  letter-spacing: 1px;
  transition: transform .2s ease-in-out;
}
.btn-guardar:hover{
  transform: scale(1.1);
}
.btn-guardar span{
  font-size: 1rem;
}
.btn-cerrar{
  position: absolute;
  top: 10px;
  right: 10px;
  height: 20px;
  width: 20px;
  border: none;
  text-align: center;
  border-radius: 50%;
  font-size: 18px;
  cursor: pointer;
  color: var(--cLink);

  transition: transform .2s ease;
}

.btn-cerrar:hover {
  transform: scale(1.3);
  background-color: var(--cLink);
  color: var(--cLight);

}

.form-input select{
  margin-top: .5rem;
  height: 25px;
  width: 90%;
  padding: 0 .5em;
}



</style>
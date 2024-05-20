<template>
<div class="box-container">
  <h1>Home páge</h1>
  
  <div class="home-container">
    <div class="title-home">
      <h1>Bienvenido a MyNotebook App</h1>
    </div>
    

    <div class="description">
      <div class="card">
        MyNotebook Address es una aplicación de gestión de contactos diseñada para ayudarte a organizar tus contactos de manera eficiente y fácil.
      </div>
      <div class="card">
        Nuestra aplicación te permite crear, ver, editar y eliminar contactos con facilidad. 
      </div>
        
    </div>
  <section>
        <p>
          ¡Comienza a usar MyNotebook Address hoy mismo y mantén tus contactos organizados y accesibles en todo momento!
        </p>  
      </section>


  </div>

    
</div>
  
</template>

<script>
export default {
  name: 'Home'

}
</script>

<style>
.title-home{
  margin-top: 2.43rem;
  text-align: center;
  margin-bottom: 2.33rem;
 /* text-shadow: 0 4px 15px var(--success);*/
}

.home-container{
  font-family: 'Inconsolata',sans-serif;
  font-size: 16px;
  text-align: center;
  margin-bottom: 2.3rem;
}

.home-container .description{
  display: flex;
  margin: 1rem 0;
  gap: 1rem;

}
.description .card{
  width: 100%;
  line-height: 1.5;
  letter-spacing: 1px;
  /*text-shadow: 0 4px 6px var(--cLink);*/
  background-color: rgba(255, 255, 255, .8);
  backdrop-filter: blur(15px);
  outline: thin  dashed var(--cLink);
  border-radius: 10px;
  padding: .5em;
}
.description .card:hover{
  box-shadow: 0px 2px 10px var(--cInfo);
}

.card p{

opacity: 1;
}
.home-container section{
  margin-top: 2.45rem;
  text-align: center;
  font-weight: 600;
  line-height: 1.5;
}
</style>
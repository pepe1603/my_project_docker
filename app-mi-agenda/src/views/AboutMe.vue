<template>
  <div class="box-container">

    <h1>About Us</h1>

      <div class="about-container">
      <h1>Acerca de MyNotebook App</h1>
      <br>
      <p>Esta App se realizo con la finalidad de implmentar dockers y consumir API de Spring, implementando Docker para contener nuestra App Full Stack. </p>

<br>
      <p>
        ¡Esperamos que disfrutes utilizando MyNotebook Address para gestionar tus contactos de manera más efectiva!
      </p>   

      <section class="box-utilities">
        <h2 class="numContactos">Tecnologias Usadas: </h2><hr>
        <ul class="tecnologias">
          <li><a href="https://vuejs.org/">Vue js</a></li>
          <li><a href="https://www.postgresql.org/">Postgresql</a></li>
          <li><a href="https://spring.io/">Spring</a></li>
          <li><a href="https://axios-http.com/docs/intro">Libreria AXIOS</a></li>
          <li><a href="https://code.visualstudio.com/">Visual Studio Code</a></li>
          <li><a href="https://code.visualstudio.com/">Docker</a></li>
          <li><a href="#">Otros...</a></li>
      </ul>
      </section>
      
    </div>
  </div>
</template>

<script>
export default {
  name: 'About'
}
</script>

<style lang="scss" >

.about-container {
  max-width: 100%;
  padding: 20px;
  text-align: justify;
}
.about-container p{
  font-family: 'Prompt',sans-serif;
}
.box-utilities{
  margin-top: 5.3rem;
  width: max-content;
  outline: thin dashed var(--cLink);
  padding: 1.5em;
  border-radius: 10px;
}

.tecnologias{
  margin-top: 1.47rem;
  display: flex;
  flex-direction: column;
  gap: .37em;
  padding: .5em;
}
.box-utilities:hover{
  box-shadow: 0px 2px 10px var(--cInfo);
}

.tecnologias li{
  padding: 2px;
  
}

.tecnologias li:hover{
  text-decoration: underline;
  
}
</style>

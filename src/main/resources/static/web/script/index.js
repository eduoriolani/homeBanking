const button = document.getElementById("button");
const { createApp } = Vue;
console.log(Vue);

createApp({
  data() {
    return {
      client: {},
      logged: true,
    };
  },
  created() {
    this.loadData();
  },
  methods: {
    loadData() {
      axios
        .get("http://localhost:8080/api/clients/current")
        .then((response) => {
          console.log(response);
          this.client = response.data;

        })
        .catch((error) => {
          this.logged = false;
        });
        
    },

    logOut(){
      axios.post('/api/logout').then(response => console.log('signed out!!!'))
    },
    mostrarMenu() {
      // Mostrar la ventana emergente
      document.getElementById("myModal").style.display = "block";
    },
    cerrarModal() {
      // Cerrar la ventana emergente
      document.getElementById("myModal").style.display = "none";
    },
  
  },
}).mount("#app");

const button = document.getElementById("button");
console.log("hola");
const { createApp } = Vue;
console.log(Vue);

createApp({
  data() {
    return {
      clients: [],
      accounts: [],
      clientData: {
        firstName: "",
        lastName: "",
        email: "",
      },
    };
  },
  created() {
    this.loadData();
  },
  methods: {
    loadData() {
      axios
        .get("http://localhost:8080/api/clients")
        .then((response) => {
          console.log(response);
          this.clients = response.data;
          console.log(this.clients);
          this.accounts = this.clients[0].accounts;
          console.log(this.accounts);
        })
        .catch((error) => {
          console.error(error);
        });
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

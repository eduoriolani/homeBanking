const button = document.getElementById("button");
const { createApp } = Vue;

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
          this.clients = response.data;
          this.accounts = this.clients.flatMap(client => client.accounts);
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

const button = document.getElementById("button");
const { createApp } = Vue;

createApp({
  data() {
    return {
      clients: [],
      accounts: [],
      owners: {},
    };
  },
  created() {
    this.loadData();
  },
  methods: {
    loadData() {
      axios
        .get("http://localhost:8080/api/clients/1")
        .then((response) => {
          this.clients = response.data;
          console.log(this.clients.accounts);
          this.accounts = this.clients.accounts;
          this.accounts.sort((a,b)=> a.balance-b.balance);
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

const button = document.getElementById("button");
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
          this.accounts = this.clients.flatMap( client => client.accounts)

          this.format = new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD',
        });
          this.accounts.forEach( e => {
            e.balance = this.format.format(e.balance)
        })
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

const button = document.getElementById("button");
const { createApp } = Vue;

createApp({
  data() {
    return {
      clients: [],
      accounts: [],
      loans: [],
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
          this.accounts = this.clients.accounts;
          this.accounts.sort((a,b)=> a.balance-b.balance);
          this.loans = this.clients.loans

          this.format = new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD',
        });
        this.accounts.forEach( e => {
            e.balance = this.format.format(e.balance)
        })
        this.loans.forEach( e => {
            e.amount = this.format.format(e.amount)
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

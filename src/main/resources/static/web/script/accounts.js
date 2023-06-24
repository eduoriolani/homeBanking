const { createApp } = Vue;

createApp({
  data() {
    return {
      client: {},
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
        .get("http://localhost:8080/api/clients/current")
        .then((response) => {
          this.client = response.data;
          this.accounts = this.client.accounts;
          this.accounts.sort((a,b)=> a.balance-b.balance);
          this.loans = this.client.loans

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

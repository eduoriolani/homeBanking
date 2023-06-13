const {createApp} = Vue 
console.log(Vue);

createApp({
    data(){
        return {
            account: [],
            clients: [],
            transactions: [],
            param: "",
            format: [],
        }
    },
    created(){
        this.loadData()

    },
    methods: {
        loadData(){
                this.param = new URLSearchParams(location.search).get("id")
                axios
                .get(`http://localhost:8080/api/accounts/${this.param}`)
                .then((response) => {
                    this.account = response.data;
                    this.transactions = this.account.transactions;
                    this.transactions.sort((a,b) => a.amount-b.amount);
                    
                    this.format = new Intl.NumberFormat('en-US', {
                        style: 'currency',
                        currency: 'USD',
                    });
                    this.transactions.forEach( e => {
                        e.amount = this.format.format(e.amount)
                    })
                    this.account.balance = this.format.format(this.account.balance)
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
        }
    },




}).mount("#app")
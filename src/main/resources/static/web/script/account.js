const {createApp} = Vue 
console.log(Vue);

createApp({
    data(){
        return {
            accounts: [],
            account: [],
            clients: [],
            transactions: [],
            param: "",
            debitType: 'text-danger',
            creditType: 'text-success',
            
        }
    },
    created(){
        this.loadData()
    },
    methods: {
        loadData(){
            // axios
            //     .get("http://localhost:8080/api/clients")
            //     .then((response) =>{
            //         this.clients = response.data;  
            //         console.log(this.clients);
            //     }),
                
                this.param = new URLSearchParams(location.search).get("id")
                axios
                .get(`http://localhost:8080/api/accounts/${this.param}`)
                .then((response) => {
                    this.account = response.data;
                    this.transactions = this.account.transactions
                    console.log(this.account);
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
    computed:{
        classTransaction(){
            return {
                debit: this.debitType && !this.creditType,
                credit: this.creditType && !this.debitType
            }
        }
    },



}).mount("#app")
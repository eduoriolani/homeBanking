const {createApp} = Vue 
console.log(Vue);

createApp({
    data(){
        return {
            account: {},
            client: {},
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
            axios
            .get(`http://localhost:8080/api/clients/current`)
            .then((response) => {
                this.client = response.data
                this.account = this.client.accounts
                console.log(this.account);
                this.param = new URLSearchParams(location.search).get("id")
                const filteredAccount = this.account.find(account => account.id == this.param)
                this.account = filteredAccount;
                console.log(this.account);
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
        }
    },




}).mount("#app")
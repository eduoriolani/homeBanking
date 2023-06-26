const {createApp} = Vue 
console.log(Vue);

createApp({
    data(){
        return {
            client: {},
            cards: [],
            debitCards: [],
            creditCards: [],

            
        }
    },
    created(){
        this.loadData()
    },
    methods: {
        loadData(){
                axios
                .get("http://localhost:8080/api/clients/current")
                .then((response) => {
                    console.log(response);
                    this.client = response.data;
                    this.cards = this.client.cards
                    console.log(this.cards);
                    this.cards.forEach( card => {    
                        card.thruDate = card.thruDate.slice(2,7);
                        card.thruDate = card.thruDate.split("-").reverse().join("/");
                    })
                    this.debitCards = this.cards.filter( card => card.type == "DEBIT");
                    console.log(this.debitCards);
                    this.creditCards = this.cards.filter( card => card.type == "CREDIT");

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
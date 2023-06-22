const {createApp} = Vue 
console.log(Vue);

createApp({
    data(){
        return {
            client: {},
            cards: [],

            
        }
    },
    created(){
        this.loadData()

    },
    methods: {
        loadData(){
                axios
                .get(`http://localhost:8080/api/clients/1`)
                .then((response) => {
                    this.client = response.data;
                    this.cards = this.client.cards
                    console.log(this.cards);
                    this.cards.forEach( card => {
                        
                        card.thruDate = card.thruDate.slice(2,7);
                        card.thruDate = card.thruDate.split("/").reverse().join("/");
                    })
                    console.log(this.cards);
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
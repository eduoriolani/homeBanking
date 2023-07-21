const {createApp} = Vue 
console.log(Vue);

createApp({
    data(){
        return {
            client: {},
            cards: [],
            debitCards: [],
            creditCards: [],
            currentDate: "",
            
        

            
        }
    },
    created(){
        this.loadData()
        this.getCurrentDate();
    },
    methods: {
        loadData(){
                axios
                .get("/api/clients/current")
                .then((response) => {
                    console.log(response);
                    this.client = response.data;
                    this.cards = this.client.cards
                    console.log(this.cards);
                    this.debitCards = this.cards.filter( card => card.type == "DEBIT");
                    console.log(this.debitCards);
                    this.creditCards = this.cards.filter( card => card.type == "CREDIT");

                })
                .catch((error) => {
                    console.error(error);
                });
            },
        getCurrentDate() {
            const getCurrentDate = new Date();
            const year = getCurrentDate.getFullYear();
            const month = getCurrentDate.getMonth() + 1;
            const day = getCurrentDate.getDate();
        
            this.currentDate = new Date(year, month - 1, day);
        
            console.log(this.currentDate);
        
            return this.currentDate;
            },
        checkExpiration(cardThruDate) {
            const limitDate = new Date(cardThruDate);
            limitDate.setMonth(limitDate.getMonth() - 1);
        
            return this.currentDate > new Date(cardThruDate);
        },
        
        warningExpirationCard(cardThruDate) {
            const limitDate = new Date(cardThruDate);
            limitDate.setMonth(limitDate.getMonth() - 1);
        
            const dateWarning = new Date(
                limitDate.getFullYear(),
                limitDate.getMonth(),
                limitDate.getDate()
            );
        
            console.log(dateWarning, "datewarning");
            console.log(this.currentDate, "currentdate");
        
            return (
                this.currentDate > dateWarning &&
                this.currentDate < new Date(cardThruDate)
            );
        },
        deleteCard(id){
            axios
            .patch("/api/clients/current/cards",`id=${id}`)
            .then(response => {
                console.log(response.data);
                
                this.loadData();
            })
            .catch(error => {
                console.log(error.response.data);
            })
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
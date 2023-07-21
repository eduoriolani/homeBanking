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
                    this.cards = this.client.cards.filter(card => card.active == true);
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
        confirmDelete(id){
            Swal.fire({
              title: `Are you sure you want to delete this card?`,
              text: "You won't be able to revert this!",
              icon: 'warning',
              showCancelButton: true,
              confirmButtonColor: '#3085d6',
              cancelButtonColor: '#d33',
              confirmButtonText: 'Yes!'
            })
            .then((result) => {
              if(result.isConfirmed){
              this.deleteCard(id);
              } else {
                Swal.fire(
                  'Card delete failed!',
                  'Please try again',
                  'error')
              }
            }) 
          },
        deleteCard(id){
            axios
            .patch("/api/clients/current/cards",`id=${id}`)
            .then(response => {
                console.log(response.data);
                Swal.fire(
                    'Card deleted!',
                    'Your card has been deleted successfully!',
                    'success'
                  );
                  this.loadData();
                })
                .catch(error => {
                  Swal.fire(
                    'Card delete failed!',
                    'Please try again',
                    'error'
                  );
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
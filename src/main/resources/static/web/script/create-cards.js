const {createApp} = Vue 
console.log(Vue);

createApp({
    data(){
        return {
            cardColor: "",
            cardType: ""

            
        }
    },
    created(){
    },
    methods: {
        createCard(){
            axios
            .post('/api/clients/current/cards','cardType=' + this.cardType +'&cardColor=' + this.cardColor)
            .then( response => {
                window.location.href = "/web/pages/cards.html"
            })
            .catch( err => {
                this.createError();

            })
        },
        
        logOut(){
            axios.post('/api/logout').then(response => console.log('signed out!!!'))
        },
          mostrarMenu() {
            // Mostrar la ventana emergente del menu
            document.getElementById("myModal").style.display = "block";
        },
        closeModal() {
            // Cerrar la ventana emergente del menu
            document.getElementById("myModal").style.display = 'none';
          },

        createError() {
            // Mostrar la ventana emergente
            document.getElementById('errorModal').style.display = 'block';
          },
          
        closeError() {
            // Cerrar la ventana emergente
            document.getElementById('errorModal').style.display = 'none';
          },
    },




}).mount("#app")
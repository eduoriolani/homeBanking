const {createApp} = Vue 
console.log(Vue);

createApp({
    data(){
        return {
            accounts: [],
            client: {},
            param: "",
            format: [],
            transfer: {
                amount: "",
                description: "",
                sourceAccount: null,
                destinationAccount: "",
            }
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
                console.log(response.data);
                this.client = response.data
                this.accounts = this.client.accounts
                console.log(this.accounts);
                
                this.format = new Intl.NumberFormat('en-US', {
                    style: 'currency',
                    currency: 'USD',
                });
                this.accounts.forEach( e => {
                    e.balance = this.format.format(e.balance)
                })
                
            })
                .catch((error) => {
                    console.error(error);
            });
        },
        transferForm() {
            axios
              .post('/api/clients/current/transactions', this.transfer)
              .then(response => {
                console.log(response);
                Swal.fire(
                  'Transfer succeeded!',
                  'You can see your updated balance.',
                  'success'
                ).then(() => {
                  window.location.href = "/web/pages/accounts.html";
                });
              })
              .catch(error => {
                console.log(error.response);
                Swal.fire(
                  'Transfer failed!',
                  error.response.data,
                  'error'
                );
              });
          },
        confirmTransfer(){
            Swal.fire({
                title: 'Are you sure to make the transfer?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes!'
              }).then((result) => {
                this.transferForm();
                
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
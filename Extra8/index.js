const {createApp} = Vue 
console.log(Vue);

createApp({
    data(){
        return {
            details: {
                accountNumber: "",
                cardNumber: "",
                amount: "",
                description: "",
                cvv: ""
            }
        }
    },
    created(){

    },
    methods: {

        confirmPayment(){
            Swal.fire({
                title: 'Are you sure you want to pay?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes!'
              }).then((result) => {
                if(result.isConfirmed){
                this.payment();
                } else {
                  Swal.fire(
                    'Request failed!',
                    'Please try again',
                    'error')
                }
              })  
        },
        payment(){
            axios
            .post(`http://localhost:8080/api/clients/cardPayment`, this.details)
            .then((response) => {
                Swal.fire(
                    'Payment succeded!',
                    'Thanks you.',
                    'success'
                  )
                })
                .catch((error) => {
                Swal.fire(
                    'Request failed!',
                    error.response.data,
                    'error'
                    );
            });
        },

    },




}).mount("#app")
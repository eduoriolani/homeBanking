const { createApp } = Vue;
console.log(Vue);

createApp({
  data() {
    return {
      client: {},
      logged: true,
      loans: [],
      activeAccounts: [],
      selectedLoan: "",
      loan: {
        loan: "",
        payment: "",
        selectedPayment: "",
        amount: "",
        account: "",
        taxPercentage: ""
      }
    };
  },
  created() {
    this.loadData();
    // this.getLoans();
  },
  methods: {
    loadData() {
      axios
        .get("/api/clients/current")
        .then((response) => {
          this.client = response.data;
          console.log(this.client);
          this.activeAccounts = this.client.accounts.filter(account => account.active == true);
          this.loans = this.client.loans;
          this.logged = true;

          this.format = new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD',
        });

        })
        .catch((error) => {
          this.logged = false;
        });
        
    },
    loanPayment(){
      this.loan.name = this.selectedLoan.name;
      console.log(this.loan.amount);
        axios
        .post("/api/loans/payment", this.loan)
        .then(response => {
          Swal.fire(
            'Loan payment succeeded!',
            'You can see your updated balance.',
            'success'
          ).then(() => {
            window.location.href = "/web/pages/accounts.html";
          });
        })
        .catch(error => {
          console.log(error.response);
          Swal.fire(
            'Request failed!',
            error.response.data,
            'error'
          );
        });
    },
    confirmLoanPayment(){
      Swal.fire({
          title: 'Are you sure to pay a due?',
          text: "You won't be able to revert this!",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes!'
        }).then((result) => {
          if(result.isConfirmed){
          this.loanPayment();
          } else {
            Swal.fire(
              'Request failed!',
              'Please try again',
              'error')
          }
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
    },
  
  },
  computed: {
    calculatePaymentAmount() {
        const amount = parseInt(this.selectedLoan.totalAmount);
        const taxPercentage = parseInt(this.selectedLoan.taxPercentage);
        const payments = parseInt(this.selectedLoan.selectedPayment);
        const paymentAmountperDue = amount / payments;
        const paymentAmount = paymentAmountperDue * this.loan.payment;

        if (!isNaN(paymentAmount)) {
          this.loan.amount = paymentAmount + (paymentAmount * taxPercentage / 100);
          return this.loan.amount.toFixed(2);
        } else {
          return '';
        }
      },
    }  
}).mount("#app");

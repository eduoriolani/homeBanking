const {createApp} = Vue 
console.log(Vue);

createApp({
    data(){
        return {
            account: {},
            id: "",
            client: {},
            transactions: [],
            filteredTransactions: [],
            param: "",
            format: [],
            fromDate: "",
            toDate: "",
        }
    },
    created(){
        this.loadData()

    },
    methods: {
        loadData(){
            axios
            .get(`/api/clients/current`)
            .then((response) => {
                this.client = response.data
                this.account = this.client.accounts
                this.param = new URLSearchParams(location.search).get("id")
                const filteredAccount = this.account.find(account => account.id == this.param)
                this.account = filteredAccount;
                console.log(this.account);
                this.transactions = this.account.transactions;
                console.log(this.transactions);
                this.transactions.sort((a, b) => {
                    const timeA = a.date;
                    const timeB = b.date;
                    
                    return timeB.localeCompare(timeA);
                  });
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
        downloadPDF(id) {
            axios
            .post('/api/accounts/pdf', null, {
                responseType: 'arraybuffer',
                params: { id: id }
            })
            .then(response => {
              const blob = new Blob([response.data], { type: 'application/pdf' });
              const url = URL.createObjectURL(blob);
      
              // Crea un enlace temporal y haz clic en él para descargar el PDF
              const link = document.createElement('a');
              link.href = url;
              link.setAttribute('download', 'account-balance.pdf');
              document.body.appendChild(link);
              link.click();
      
              // Liberar el objeto URL creado
              URL.revokeObjectURL(url);
            })
            .catch(error => {
              // Manejar errores en caso de ser necesario
              console.error('Error trying to download the PDF:', error);
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
    computed: {
        filterByDate(){
            if(this.fromDate != "" && this.toDate != ""){
                 this.filteredTransactions = this.transactions.filter(transaction => transaction.date.substring(2,10)  <= this.dateEnd.substring(2) &&
                                                                      transaction.date.substring(2,10) >= this.dateStart.substring(2))
            }
            if(this.fromDate == "" && this.toDate == ""){
                this.filteredTransactions = this.transactions
            }
        }
    }



}).mount("#app")
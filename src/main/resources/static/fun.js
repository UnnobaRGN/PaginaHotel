function elim(id){
    swal({
        title: "Esta seguro desea cancelar esta reserva?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((OK) => {
            if (OK) {
                $.ajax({
                    url:"/cancelar/"+id,
                    success: function (res) {
                        console.log(res);
                    }
                });
                swal("Se ha cancelado la reserva", {
                    icon: "success",
                }).then((ok)=>{
                    if(ok){
                        location.href="/Bookings/ConsultaReservas";
                    }
                });
            } else {
                swal("No se ha cancelado la reserva");
            }
        });
}

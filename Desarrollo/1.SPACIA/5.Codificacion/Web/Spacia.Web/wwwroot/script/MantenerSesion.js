var pathAbsoluto;

jQuery(window).load(function () {
    var rutaProyecto = '@Request.ApplicationPath';
    if (rutaProyecto != "/") {
        rutaProyecto += "/";
    }
    IniciarTimer(rutaProyecto + "/");
});

function IniciarTimer(path){
	pathAbsoluto=path;
    var idleInterval = setInterval("timerLlamado()", 1000*60); 
};

function timerLlamado()
{
    jQuery.ajax({
        type: "POST",
        async: false,
        url: rutaProyecto + "Principal/SesionPermanente",
        success: function (vacio) {
            console.log("sesion vehiculo ok");
        },
        error: function (xhr, status, error) {
            console.log("sesion vehiculo no encontrada");
            jQuery('#modal-caduco').modal('show');
        }
    });
}
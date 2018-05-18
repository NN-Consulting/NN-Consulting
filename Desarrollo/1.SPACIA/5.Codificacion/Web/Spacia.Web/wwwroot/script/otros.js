function MensajeError(contenidoMensaje, position) {
    var opts = {
        "closeButton": true,
        "debug": false,
        "positionClass": "toast-top-" + position + "",
        "onclick": null,
        "timeOut": "10000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    toastr.warning(contenidoMensaje, "", opts);
}

function MensajeConfirmacion(contenidoMensaje, position) {
    var opts = {
        "closeButton": true,
        "debug": false,
        "positionClass": "toast-bottom-"+position+"",
        "onclick": null,
        "timeOut": "5000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    toastr.success(contenidoMensaje, "", opts);
}
function jsRemoveWindowLoad() {
    if ($("div#divLoading").hasClass('show')) {
        $("div#divLoading").removeClass('show');
    }

}

function jsShowWindowLoad() {
    $("div#divLoading").addClass('show');

}
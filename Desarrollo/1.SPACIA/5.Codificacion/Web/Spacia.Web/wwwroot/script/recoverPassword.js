function initControls()
{
    $("#btnRecover").click(function(){
        var email = $('#iptUsuario').val();
        var entradaValida = true;

        if(email == null || email == "")
        {
            entradaValida = false;
        }
        if(entradaValida)
        {
            jsShowWindowLoad();
            $.ajax({
                type: 'POST',
                url: '/Home/RecuperarContrasena',
                dataType: 'json',
                data: {
                    email: email
                },
                sync: true,
                success: function (jsonCombos) {
                    var retorno = jsonCombos 
                    if(retorno != "")
                    {
                        MensajeConfirmacion(retorno, 'right');
                    }
                    else
                    {
                        MensajeError("El correo no fue enviado exitosamente. Intente nuevamente. ", 'right');

                    }
                },
                complete: function () {
                    jsRemoveWindowLoad();
                },
                error: function (xhr, status, error) {
                    jsRemoveWindowLoad();
                    $("#DivErrores").html(xhr.responseText);
                    $('#Errores').modal("show");
                }
            });
        }
        else
        {
            MensajeError('Ingrese correctamente el correo eléctronico. ', 'right');
        }
    });
}
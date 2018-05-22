
function initControls()
{
    $("#btnIniciarSesion").click(function(){
        //Evaluar los accesos en backend

        var email = $('#iptUsuario').val();
        var password = $('#iptContrasena').val();
        var entradaValida = true;

        if(email == null || email == "" || password == null || password == "")
        {
            entradaValida = false;
        }
        if(entradaValida)
        {
            jsShowWindowLoad();
            $.ajax({
                type: 'POST',
                url: '/Home/IniciarSesion',
                dataType: 'json',
                data: {
                    email: email,
                    password: password
                },
                sync: true,
                success: function (jsonCombos) {
                    var retorno = jsonCombos.comunicacion; 
                    if(!retorno.error)
                    {
                        MensajeConfirmacion(retorno.mensaje, 'right');
                        //sessionStorage.setItem("token", jsonCombos.data.access_token); 
                        window.location.href = '../Home/Panel';
                    }
                    else
                    {
                        MensajeError(retorno.mensaje, 'right');
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
            MensajeError('Ingrese correctamente su usuario y contraseña. ', 'right');
        }
    });
}
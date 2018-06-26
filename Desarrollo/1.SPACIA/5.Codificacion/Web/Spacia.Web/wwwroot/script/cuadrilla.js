function CargarTipoAtencion() {
    jsShowWindowLoad();
    $.ajax({
        type: 'POST',
        url: rutaProyecto + 'Principal/ObtenerTipoSupervision',
        contentType: 'application/json; charset=utf-8',
        sync: false,
        success: function (jsonCombos) {
            var listado = "";

            for (i = 0; i < jsonCombos.length; i++) {
                var selected = "";
                if (i == 0)
                    selected = "selected";
                listado = listado + "<option value='" + jsonCombos[i].codigo + "' " + selected + ">" + jsonCombos[i].descripcion + "</option>";
            }
            $('#cboTipoAtencion').html(listado);
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
function CargarListadoCuadrillas() {
    var tipoAtencion = $('#cboTipoAtencion').val();
    var table = $('#listadoCuadrillas').DataTable();
    $('#listadoCuadrillas').empty();
    table.destroy();

    var columns =
                [
                    { "data": "nroCuadrilla", "width": "15%", "sClass": "text-left" },
                    { "data": "descripcionCuadrilla", "width": "20%", "sClass": "text-left" },
                    { "data": "accion", "width": "5%" }
                ];

    $('#listadoCuadrillas').on('xhr.dt', function (e, settings, json, xhr) {
    }).dataTable({

        fixedHeader: {
            header: true,
            footer: true
        },
        "responsive": true,
        "ordering": false,
        "searching": false,
        "pageLength": 20,
        "lengthChange": false,
        "ajax": {
            "url": rutaProyecto + 'Principal/ObtenerListaCuadrilla',
            "data": {
                "tipoAtencion": tipoAtencion
            },
            "type": 'POST',
            beforeSend: function () {
                jsShowWindowLoad();
            },
            complete: function () {
                jsRemoveWindowLoad();
            },
            error: function handleAjaxError(xhr, textStatus, error) {
                jsRemoveWindowLoad();
                $("#DivErrores").html(xhr.responseText);
                $('#Errores').modal("show");
            }
        },
        "columns": columns,
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        "columnDefs": [{
            "targets": -1,
            "data": null,
            "render": function (data, type, full, meta) {
                var boton = "<button type='button' class='btn btn-info'  onClick=\"EditarCuadrilla('" + full['nroCuadrilla'].trim() + "');\" ><i class='fa fa-pencil fa-lg' data-toggle='tooltip' title='Editar'></i></button>";
                return boton;
            }
        }]
    });
}
function EditarCuadrilla(idCuadrilla) {
    console.log(idCuadrilla);
}

function ModalAñadirCuadrilla() {
    $('#modalCuadrilla').modal('show');
}
function InsertarCuadrilla() {
    var colorCuadrilla = $('#txtColorCuadrilla').val();
    console.log(colorCuadrilla);
}


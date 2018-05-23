/*  Variables globales  */

const monthNames = ["Ene", "Feb", "Mar", "Abr", "May", "Jun","Jul", "Ago", 
                        "Sep", "Oct", "Nov", "Dec"];
const weekNames = ["lun", "mar", "mié", "jue", "vie", "sab","dom"];
var startOfWeek = moment().startOf('isoWeek').toDate();
var endOfWeek   = moment().endOf('isoWeek').toDate();
var currentWeek = [];

function initControlsPanel()
{
    updateDateMonth();
    $("#left_arrow").click(function(){
        startOfWeek.setDate(startOfWeek.getDate()-7);
        endOfWeek.setDate(endOfWeek.getDate()-7);
        updateDateMonth();
        initHeaderMonth();
    });  
    $("#right_arrow").click(function(){
        startOfWeek.setDate(startOfWeek.getDate()+7);
        endOfWeek.setDate(endOfWeek.getDate()+7);
        updateDateMonth();
        initHeaderMonth();
    });
    initHeaderMonth();           
}
function updateDateMonth(){
    /* Formato : 15 Mar - 22 Mar */
    $(".lbl_month").html(startOfWeek.getDate()+ ' ' + 
                            monthNames[startOfWeek.getMonth()] + ' - ' + 
                                endOfWeek.getDate()+ ' ' + 
                                    monthNames[endOfWeek.getMonth()]);

    /* Fechas entre : 15 Mar - 22 Mar */
    currentWeek = [];
    for(var i=0 ; i<7 ; i++){
        var currDate = new Date ();
        currDate.setDate(startOfWeek.getDate()+i);
        currDate.setMonth(startOfWeek.getMonth());
        currDate.setFullYear(startOfWeek.getFullYear());
        currentWeek.push(currDate);
    }
}

function initHeaderMonth(){
    var table = $('#tablaAgenda').DataTable();
    $('#tablaAgenda').empty();
    table.destroy();
    var columns =
    [
        { "data": "cantLunes", "width": "14%", "sClass": "text-center " },
        { "data": "cantMartes", "width": "14%", "sClass": "text-center" },
        { "data": "cantMiercoles", "width": "14%", "sClass": "text-center" },
        { "data": "cantJueves", "width": "14%", "sClass": "text-center" },
        { "data": "cantViernes", "width": "14%", "sClass": "text-center" },
        { "data": "cantSabado", "width": "14%", "sClass": "text-center" },
        { "data": "cantDomingo", "width": "14%", "sClass": "text-center" }
    ];
    $('#tablaAgenda').on('xhr.dt', function (e, settings, json, xhr) {
        //json.aaData.length;
        $('#ambiente_cantidad').html(json.aaData.length+' ambientes');
        $('#ambientes_wrap').html('');
        for(index in json.aaData)
        {
            $('#ambientes_wrap').append('<div class="ambiente_wrap_body" style="height:60px;text-align:left;padding:10px;">'
                                        +' <span class="ambiente_header">'+json.aaData[index].name+'</span><br/>'
                                        +'<span class="ambiente_body" style="padding: 5px;">Capacidad: '+json.aaData[index].capacity+' ambientes</span></div>');
        }
    }).dataTable({
        fixedHeader: {
            header: true,
            footer: true
        },
        "responsive": true,
        "ordering": false,
        "bSort": false,
        "searching": false,
        "paging":   false,
        "ordering": false,
        "info":     false,
        "lengthChange": false,
        "ajax": {
            "url": "/Home/ObtenerListaAgendamiento",
            "data": {
                "date": formatDate(startOfWeek)
            },
            "type": 'POST',
            beforeSend: function () {
                jsShowWindowLoad();
            },
            complete: function () {
                var nRow =  $('#tablaAgenda thead tr th')[0];
                $(nRow).removeClass('sorting_asc');
                for(index in currentWeek)
                {
                    var head_item = table.columns(index).header();
                    $(head_item).html('<span class="day_header">'+currentWeek[index].getDate()+'</span><br/><span class="day_desc_header">'+weekNames[index]+'</span>');
                }
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
        }
        ,
        "columnDefs": [
        {
            "targets": 0,
            "data": null,
            "orderable": false,
            "bSortable": false,
            "render": function (data, type, full, meta) {
                var boton = "";
                if(full['cantLunes']>0)
                {
                    boton = "<div class='day_wrap_body'><span class='day_exist_body'> <a class='no_decoration' href='javascript:void(0);' ";
                    boton += "onclick=\"ObtenerTitutloModal(0,"+full['room_id']+", "+full['capacity']+",'"+full['name']+"');\" ";
                    boton += ">"+full['cantLunes']+"</a></span></div>"; 
                } 
                else
                    boton = "<div class='day_wrap_body'><span class='day_no_exist_body'>"+full['cantLunes']+"</span></div>";                
                return boton;
            },
         },
         {
            "targets": 1,
            "data": null,
            "render": function (data, type, full, meta) {
                var boton = "";
                if(full['cantMartes']>0)
                {
                    boton = "<div class='day_wrap_body'><span class='day_exist_body'> <a class='no_decoration' href='javascript:void(0);' ";
                    boton += "onclick=\"ObtenerTitutloModal(1,"+full['room_id']+", "+full['capacity']+",'"+full['name']+"');\" ";
                    boton += ">"+full['cantMartes']+"</a></span></div>"; 
                }
                else
                    boton = "<div class='day_wrap_body'><span class='day_no_exist_body'>"+full['cantMartes']+"</span></div>";                
                return boton;
            },
         },
         {
            "targets": 2,
            "data": null,
            "render": function (data, type, full, meta) {
                var boton = "";
                if(full['cantMiercoles']>0)
                {
                    boton = "<div class='day_wrap_body'><span class='day_exist_body'><a class='no_decoration' href='javascript:void(0);' ";
                    boton += "onclick=\"ObtenerTitutloModal(2,"+full['room_id']+", "+full['capacity']+",'"+full['name']+"');\" ";
                    boton += ">"+full['cantMiercoles']+"</a></span></div>"; 
                }
                else
                    boton = "<div class='day_wrap_body'><span class='day_no_exist_body'>"+full['cantMiercoles']+"</span></div>";                
                return boton;
            },
         },
         {
            "targets": 3,
            "data": null,
            "render": function (data, type, full, meta) {
                var boton = "";
                if(full['cantJueves']>0)
                {
                    boton = "<div class='day_wrap_body'><span class='day_exist_body'><a class='no_decoration' href='javascript:void(0);' ";
                    boton += "onclick=\"ObtenerTitutloModal(3,"+full['room_id']+", "+full['capacity']+",'"+full['name']+"');\" ";
                    boton += ">"+full['cantJueves']+"</a></span></div>"; 
                }
                else
                    boton = "<div class='day_wrap_body'><span class='day_no_exist_body'>"+full['cantJueves']+"</span></div>";                
                return boton;
            },
         },
         {
            "targets": 4,
            "data": null,
            "render": function (data, type, full, meta) {
                var boton = "";
                if(full['cantViernes']>0)
                {
                    boton = "<div class='day_wrap_body'><span class='day_exist_body'><a class='no_decoration' href='javascript:void(0);' ";
                    boton += "onclick=\"ObtenerTitutloModal(4,"+full['room_id']+", "+full['capacity']+",'"+full['name']+"');\" ";
                    boton += ">"+full['cantViernes']+"</a></span></div>"; 
                }
                else
                    boton = "<div class='day_wrap_body'><span class='day_no_exist_body'>"+full['cantViernes']+"</span></div>";                
                return boton;
            },
         },
         {
            "targets": 5,
            "data": null,
            "render": function (data, type, full, meta) {
                var boton = "";
                if(full['cantSabado']>0)
                {
                    boton = "<div class='day_wrap_body'><span class='day_exist_body'><a class='no_decoration' href='javascript:void(0);' ";
                    boton += "onclick=\"ObtenerTitutloModal(5,"+full['room_id']+", "+full['capacity']+",'"+full['name']+"');\" ";
                    boton += ">"+full['cantSabado']+"</a></span></div>";
                } 
                else
                    boton = "<div class='day_wrap_body'><span class='day_no_exist_body'>"+full['cantSabado']+"</span></div>";                
                return boton;
            },
         },
         {
            "targets": 6,
            "data": null,
            "render": function (data, type, full, meta) {
                var boton = "";
                if(full['cantDomingo']>0)
                {
                    boton = "<div class='day_wrap_body'><span class='day_exist_body'><a class='no_decoration' href='javascript:void(0);' ";
                    boton += "onclick=\"ObtenerTitutloModal(6,"+full['room_id']+", "+full['capacity']+",'"+full['name']+"');\" ";
                    boton += ">"+full['cantDomingo']+"</a></span></div>"; 
                }
                else
                    boton = "<div class='day_wrap_body'><span class='day_no_exist_body'>"+full['cantDomingo']+"</span></div>";                
                return boton;
            },
        }]
    });
}
function ObtenerTitutloModal(idFecha, idAmbiente, capacidadAmbiente, descripcionAmbiente){
    var fechaAmbiente = formatDate(currentWeek[idFecha]);
    $('#ambientes_wrap_name').html('');
    $('#ambientes_wrap_name').append('<div class="ambiente_wrap_modal"><span class="ambiente_header">'
                                    +descripcionAmbiente+'</span><br/><span class="ambiente_body">Capacidad: '
                                    +capacidadAmbiente+' ambientes</span></div><button class="buton-Nuevo" type="button" onclick="nuevo_()" name="nuevo" id="nuevo">nuevo +</button><br>');
    jsShowWindowLoad();
    $.ajax({
        type: 'POST',
        url: '/Home/ObtenerEventosAmbienteFecha',
        dataType: 'json',
        data: {
            idAmbiente:idAmbiente,
            fechaAmbiente: fechaAmbiente
        },
        success: function (data) {
            var listadoEventoDetalle = data;
            console.log(listadoEventoDetalle);
            //$('#modalEventos').modal('show'); //Mostrar Modal
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

function nuevo_(){
    MensajeError("Opción en Desarrollo. ", "right");
}

function CerrarSesion()
{
    jsShowWindowLoad();
    $.ajax({
        type: 'POST',
        url: '/Home/CerrarSesion',
        dataType: 'json',
        data: {
            //token:token
        },
        success: function (data) {
            //sessionStorage.setItem('token',"");
            window.location.href = '../Home/Panel';
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


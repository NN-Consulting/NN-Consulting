using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Spacia.Web.Clases;
using Spacia.Web.Models;
using Microsoft.AspNetCore.Http;

namespace Spacia.Web.Controllers
{
    public class HomeController : Controller
    {
        private EventsRESTService service = new EventsRESTService();

        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Login()
        {
            return View("Login");
        }

        public IActionResult RecoverPassword(){
            
            return View("RecoverPassword");
        }
        public IActionResult Panel()
        {
            var loginDto = GrabarSesion.TheData;
            if(loginDto != null && loginDto.data != null)
            {
                if(!String.IsNullOrEmpty(loginDto.data.access_token))
                    return View("Panel");
                else
                    return View("Login");
            }
            else
            {
                return View("Login");
            }
        }
        [HttpPost]
        public ActionResult IniciarSesion(LoginModel loginDto)
        {
            loginDto = service.PostIniciarSesion(loginDto);
            loginDto.comunicacion = new ComunicacionModel();
            if (!String.IsNullOrEmpty(loginDto.error))
            {
                loginDto.comunicacion.mensaje = loginDto.error;
                loginDto.comunicacion.error = true;
            }
            else if (!String.IsNullOrEmpty(loginDto.message))
            {
                loginDto.comunicacion.mensaje = loginDto.message;
                loginDto.comunicacion.error = false;
                GrabarSesion.TheData = loginDto;
            }
            else
            {
                loginDto.comunicacion.mensaje = "Ocurrió un error en la comunicación con el servidor. ";
                loginDto.comunicacion.error = true;
            }
            return Json(loginDto);
        }

        [HttpPost]
        public ActionResult CerrarSesion()
        {
            var myData = GrabarSesion.TheData;
            service.PostCerrarSesion(myData.data.access_token);
            GrabarSesion.TheData = new LoginModel();
            Response.Headers.Append("Cache-Control", "no-cache, no-store, must-revalidate");
            Response.Headers.Append("Pragma", "no-cache");
            Response.Headers.Append("Expires", "0");
            return Json("");
        }

        [HttpPost]
        public ActionResult RecuperarContrasena(LoginModel loginDto)
        {
            string response = service.PostRecuperarContrasena(loginDto);
            return Json(response);
        }

        [HttpPost]
        public ActionResult ObtenerListaAgendamiento(string date)
        {
            var myData = GrabarSesion.TheData;
            FiltroEventoModel filtroEvento = new FiltroEventoModel();
            filtroEvento.date = date;
            AgendamientoModel agendaMientoDto = service.PostEvents(filtroEvento,myData.data.access_token);
            int i = 0;
            foreach(EventoModel eventoDto in agendaMientoDto.data.events)
            {
                if(eventoDto.details.Count>0)
                {
                    foreach(DetailsModel detalleModel in eventoDto.details)
                    {
                        foreach (AmbienteModel roomDto in agendaMientoDto.data.rooms)
                        {
                            if(detalleModel.room_id == roomDto.room_id)
                            {
                                switch(i)
                                {
                                    case 0: roomDto.cantLunes = detalleModel.num_events;break;
                                    case 1: roomDto.cantMartes = detalleModel.num_events; break;
                                    case 2: roomDto.cantMiercoles = detalleModel.num_events; break;
                                    case 3: roomDto.cantJueves = detalleModel.num_events; break;
                                    case 4: roomDto.cantViernes = detalleModel.num_events; break;
                                    case 5: roomDto.cantSabado = detalleModel.num_events; break;    
                                    case 6: roomDto.cantDomingo = detalleModel.num_events; break;      
                                }
                            }
                        }
                    }
                }
                i++;
            }
            return Json(new
            {
                sEcho = Request.Query["draw"],
                iTotalRecords = agendaMientoDto.data.rooms.Count,
                iTotalDisplayRecords = agendaMientoDto.data.rooms.Count,
                aaData = agendaMientoDto.data.rooms
            });
        }

        [HttpPost]
        public ActionResult ObtenerEventosAmbienteFecha(string fechaAmbiente, int idAmbiente)
        {
            var myData = GrabarSesion.TheData;
            FiltroEventoDetalleModel filtroEvento = new FiltroEventoDetalleModel();
            filtroEvento.date = fechaAmbiente;
            filtroEvento.room_id = idAmbiente;
            DetalleModel dataDetalleEventos = service.PostDetalleEvents(filtroEvento, myData.data.access_token);
            return Json(dataDetalleEventos);
        }

        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}

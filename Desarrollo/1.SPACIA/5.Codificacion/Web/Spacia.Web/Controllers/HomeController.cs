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
        public ActionResult ObtenerListaAgendamiento(/*string name*/)
        {
            //List<AgendamientoModel> listadoAgendamientoDto = service.PostEvents(name);
            //List<AgendamientoModel> listadoAgendamientoDto = service.PostEvents();
            //listadoAgendamientoDto = service.GetEvents();

            List<AgendamientoModel> listadoAgendamiento = new List<AgendamientoModel>();
            AgendamientoModel eventoAgendado = new AgendamientoModel();
            eventoAgendado.idEvento = "1";
            eventoAgendado.descEvento = "Sala de Conferencias";
            eventoAgendado.cantLunes = 2;
            eventoAgendado.cantDomingo = 5;
            eventoAgendado.capacidad = 30;
            listadoAgendamiento.Add(eventoAgendado);

            eventoAgendado = new AgendamientoModel();
            eventoAgendado.idEvento = "2";
            eventoAgendado.descEvento = "Sala de Reuniones 1";
            eventoAgendado.cantMiercoles = 2;
            eventoAgendado.cantMartes = 5;
            eventoAgendado.capacidad = 35;
            listadoAgendamiento.Add(eventoAgendado);

            return Json(new
            {
                sEcho = Request.Query["draw"],
                iTotalRecords = listadoAgendamiento.Count,
                iTotalDisplayRecords = listadoAgendamiento.Count,
                aaData = listadoAgendamiento
            });
        }

        [HttpPost]
        public ActionResult IniciarSesion(LoginModel loginDto)
        {
            loginDto = service.PostIniciarSesion(loginDto);
            loginDto.comunicacion = new ComunicacionModel();
            if(!String.IsNullOrEmpty(loginDto.error)){
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
                loginDto.comunicacion.mensaje = "No existe respuesta del servidor. ";
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

        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}

using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Spacia.Web.Models;

namespace Spacia.Web.Controllers
{
    public class HomeController : Controller
    {
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
            return View("Panel");
        }

        [HttpPost]
        public ActionResult ObtenerListaAgendamiento()
        {
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

        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}

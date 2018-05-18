using System;
namespace Spacia.Web.Models
{
    public class AgendamientoModel
    {
        public string idEvento = "";
        public string nroEvento = "";
        public string descEvento = "";
        public int cantLunes = 0;
        public int cantMartes = 0;
        public int cantMiercoles = 0;
        public int cantJueves = 0;
        public int cantViernes = 0;
        public int cantSabado = 0;
        public int cantDomingo = 0;
        public int capacidad = 0;

        //tests
        public int id { get; set; }
        public int userId { get; set; }
        public string title { get; set; }
        public string body { get; set; }
    }
}

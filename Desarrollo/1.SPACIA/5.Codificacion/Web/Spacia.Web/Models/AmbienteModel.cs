using System;
namespace Spacia.Web.Models
{
    public class AmbienteModel
    {
        public int room_id { get; set; }
        public string name { get; set; }
        public int capacity { get; set; }

        public int cantLunes { get; set; }
        public int cantMartes { get; set; }
        public int cantMiercoles { get; set; }
        public int cantJueves { get; set; }
        public int cantViernes { get; set; }
        public int cantSabado { get; set; }
        public int cantDomingo { get; set; }
    }
}

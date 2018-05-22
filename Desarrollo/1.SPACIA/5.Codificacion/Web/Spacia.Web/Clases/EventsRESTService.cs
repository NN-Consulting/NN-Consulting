using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using Newtonsoft.Json;
using Spacia.Web.Models;

namespace Spacia.Web.Clases
{
    public class EventsRESTService
    {
        public List<AgendamientoModel> GetEvents()
        {
            string uri = "https://jsonplaceholder.typicode.com/posts/";

            using (WebClient webClient = new WebClient())
            {
                return JsonConvert.DeserializeObject<List<AgendamientoModel>>(
                    webClient.DownloadString(uri)
                );
            }
        }

        //public List<AgendamientoModel> PostEvents(string data)
        public List<AgendamientoModel> PostEvents()
        {
            string uri = "https://jsonplaceholder.typicode.com/posts/";

            using (WebClient webClient = new WebClient())
            {
                try
                {
                    webClient.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
                    //string htmlResult = webClient.UploadString(uri, data);
                    string htmlResult = webClient.UploadString(uri,"");
                    AgendamientoModel flight = new AgendamientoModel();
                    flight = JsonConvert.DeserializeObject<AgendamientoModel>(htmlResult);
                    return new List<AgendamientoModel>();
                }
                catch(Exception e)
                {
                    throw e;
                }
            }
        }

        public LoginModel PostIniciarSesion(LoginModel loginDto)
        {
            string uri = "http://206.81.6.211/api/v1/login";
            var data = JsonConvert.SerializeObject(loginDto);
            using (WebClient webClient = new WebClient())
            {
                try
                {
                    webClient.Headers[HttpRequestHeader.ContentType] = "application/json";
                    webClient.Headers[HttpRequestHeader.Accept] = "application/json";

                    string htmlResult = webClient.UploadString(uri, data);

                    LoginModel loginResponse = new LoginModel();
                    loginResponse = JsonConvert.DeserializeObject<LoginModel>(htmlResult);
                    loginResponse.email = loginDto.email;
                    loginResponse.password = loginDto.password;

                    return loginResponse;
                }
                catch (WebException ex)
                {
                    switch (ex.Status)
                    {
                        case WebExceptionStatus.ConnectFailure:
                            throw ex;
                        case WebExceptionStatus.Timeout:
                            throw ex;
                        case WebExceptionStatus.NameResolutionFailure:
                            throw ex;
                        case WebExceptionStatus.ProtocolError:
                            if (ex.Message == "The remote server returned an error: (401) Unauthorized.")
                            {
                                LoginModel loginResponse = new LoginModel();
                                loginResponse.error = "Usuario no autorizado. ";
                                return loginResponse;
                            }
                            else{
                                throw ex;
                            }
                        default:
                            throw ex;
                    }
                }
            }
        }

        public void PostCerrarSesion(string token)
        {
            string uri = "http://206.81.6.211/api/v1/logout";
            var data = JsonConvert.SerializeObject(token);
            using (WebClient webClient = new WebClient())
            {
                try
                {
                    webClient.Headers[HttpRequestHeader.ContentType] = "application/json";
                    webClient.Headers[HttpRequestHeader.Accept] = "application/json";
                    webClient.Headers[HttpRequestHeader.Authorization] = "Bearer "+token;

                    string htmlResult = webClient.UploadString(uri, "");

                }
                catch (WebException ex)
                {
                    switch (ex.Status)
                    {
                        case WebExceptionStatus.ConnectFailure:
                            throw ex;
                        case WebExceptionStatus.Timeout:
                            throw ex;
                        case WebExceptionStatus.NameResolutionFailure:
                            throw ex;
                        case WebExceptionStatus.ProtocolError:
                            throw ex;
                        default:
                            throw ex;
                    }
                }
            }
        }
    }
}

<body>
<div class="email-background" style="max-width: 570px;background-color: white;margin: auto;">

    <div class="pre-header" style="color: white;">
        <p>@yield('pre_header')</p>
    </div>

    <div class="email-container"
         style="width: 100%;font-family: Verdana, Helvetica, sans-serif;color: #212121;font-size: 14px;font-weight: 500;">

        <div class="logo-section" style="max-width: 170px;margin: 0 auto;">
            <img src="{{ asset('storage/images/emails/logo_horizontal.png') }}" alt="Pichanga" style="width: 100%;">
        </div>

        @yield('content')


        <div class="goodbye" style="margin: 60px 0">
            <p>@yield('signature')</p>
            <p>- <b>Tus amigos de Pichanga.</b></p>
        </div> <!-- End Goodbye -->

        <div class="separator" style="height: 1px;background-color: #D8D8D8;margin:40px 0 40px;"></div>
        <!-- Separator -->

        <div class="social" style="margin: 0 32px 32px;">
            <center><p style="text-align: center; width: 300px; margin: 32px 0 32px">Contáctanos a través de nuestra app
                    o en nuestras redes
                    sociales:</p></center>
            <table id="apps" align="center">
                <tr>
                    <td>
                        <div class="download-app" style="margin: 0 16px;">
                            <a href="#" style="text-decoration: none;color: #24A31D;">
                                <img src="{{ asset('storage/images/emails/Pichanga_app_store.png') }}"
                                     alt="Descargar desde el App Store" style="width: 100%;">
                            </a>
                        </div> <!-- End First Download App -->
                    </td>

                    <td>
                        <div class="download-app" style="margin: 0 16px;">
                            <a href="#" style="text-decoration: none;color: #24A31D;">
                                <img src="{{ asset('storage/images/emails/Pichanga_play_store.png') }}"
                                     alt="Descargar desde el Play Store" style="width: 100%;">
                            </a>
                        </div> <!-- End Second Download App -->
                    </td>
                </tr> <!-- End First Row of Apps -->
            </table>
            <table align="center" style="margin-top:16px">
                <tr>
                    <td>
                        <div class="download-app" style="margin: 0 16px;">
                            <a href="#">
                                <img src="{{ asset('storage/images/emails/facebook.png') }}">
                            </a>
                        </div>
                    </td>

                    <td>
                        <div class="download-app" style="margin: 0 16px;">
                            <a href="#">
                                <img src="{{ asset('storage/images/emails/twitter.png') }}">
                            </a>
                        </div>
                    </td>
                </tr> <!-- End Second Row of Apps -->
            </table> <!-- End Apps -->

        </div> <!-- End Social -->

        <div class="separator" style="height: 1px;background-color: #D8D8D8;margin:40px 0 40px;"></div>
        <!-- Separator -->

        @include('emails.partials.footer')

    </div> <!-- End Email Container -->
</div>
</body>
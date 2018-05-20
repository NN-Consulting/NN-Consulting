@extends('layouts.mail')

@section('pre_header', "Bienvenido a Pichanga! Gracias por unirte a la red más grande de pichangueros del país.")

@section('content')

    <div class="greeting-section" style="font-size: 20px; font-weight: 700; line-height: 30px">
        Hola <span class="user-name" style="color: #3FAF39;">{{ $user->first_name }}</span>,
    </div>
    <div class="banner-section" style="margin: 16px 0">
        <img src="{{ asset('storage/images/emails/img_email.png') }}" alt="Reestablecer contraseña" style="width: 100%;">
    </div>

    <div class="main-text" style="font-size: 14px;line-height: 24px;font-weight: 500;margin: 16px 0;">
        <p>
            <b>¡Bienvenido!</b>, Pichanga es la mejor opción para la reserva de canchas. <br>
            Ya puedes comenzar a disfrutar de tus pichangas favoritas con tus amigos. Olvidate de tener que ir y
            pagar en el local.. ahora sólo busca, reserva y paga online de forma rápida y <b>segura</b>!
        </p>
    </div> <!-- End Main Text -->

    <div class="features-cta"
         style="background-color: rgba(36, 163, 29, 0.2);padding: 20px;border-radius: 8px;
         margin: 16px 0 16px 0;font-size: 20px;text-align: center;line-height: 28px;">
        <div class="features-cta__regular"
             style="font-weight: 500;">¿Qué puedes hacer con
            Pichanga?
        </div>
        <div class="features-cta__highlighted"
             style="font-weight: bold;color: #24A31D;">Ni bien ingreses a la app,
            podrás:
        </div>
    </div> <!-- end Features CTA -->

    <div class="features" style="text-align: center;">
        <table align="center">
            <tr>
                <td>
                    <table class="row">
                        <tr>
                            <td>
                                <div class="feature-col">
                                    <div class="feature__header"
                                         style="color: #24A31D;font-size: 20px;font-weight: bold;line-height: 24px;margin-bottom: 10px;">
                                        1. Descubrir canchas
                                    </div>
                                    <div class="feature__text"
                                         style="font-size: 14px;font-weight: 500;line-height: 18px;color: #212121;width: 232px;text-align: center">
                                        Encuentra todas las canchas disponibles según tu
                                        ubicación.
                                    </div>
                                </div>
                            </td>
                            <td>
                                <img src="{{ asset('storage/images/emails/encuentra_canchas.png') }}"
                                     alt="Encuentra canchas" style="width: 100%;">
                            </td>
                        </tr>
                    </table>
                </td>
            </tr><!-- End First Row -->
            <tr>
                <td>
                    <table id="row">
                        <tr>
                            <td>
                                <img src="{{ asset('storage/images/emails/reserva_paga.png') }}"
                                     alt="Encuentra canchas" style="width: 100%;">
                            </td>
                            <td>
                                <div class="column-helper" style="width: auto;">
                                    <div class="feature__header"
                                         style="color: #24A31D;font-size: 20px;font-weight: bold;line-height: 24px;margin-bottom: 10px;">
                                        2. Reservar y pagar
                                    </div>
                                    <div class="feature__text"
                                         style="font-size: 14px;font-weight: 500;line-height: 18px;color: #212121;width: 232px; text-align: center">
                                        Reserva tus canchas y paga directamente desde la app.
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr><!-- End Second Row -->
        </table><!-- End Two Rows -->
    </div><!-- End Features -->
@endsection

@section('signature', 'Esperamos que distrutes de tus pichangas con nosotros.')
<?php

namespace App\Notifications;

use Illuminate\Bus\Queueable;
use Illuminate\Notifications\Notification;
use Illuminate\Notifications\Messages\MailMessage;

class MailResetPasswordToken extends Notification {

    use Queueable;

    public $token;
    public $firstName;

    /**
     * Create a new notification instance.
     *
     * @param $token
     * @param $firstName
     */
    public function __construct($token, $firstName)
    {
        $this->token = $token;
        $this->firstName = $firstName;
    }

    /**
     * Get the notification's delivery channels.
     *
     * @param  mixed $notifiable
     * @return array
     */
    public function via($notifiable)
    {
        return ['mail'];
    }

    /**
     * Get the mail representation of the notification.
     *
     * @param  mixed $notifiable
     * @return \Illuminate\Notifications\Messages\MailMessage
     */
    public function toMail($notifiable)
    {
        return (new MailMessage)
            // TODO check view.
            ->subject("Reestablecer contraseña")
            ->view('emails.forgot_password', ['generationLink' => url('password/reset', $this->token)]);
    }

    /**
     * Get the array representation of the notification.
     *
     * @param  mixed $notifiable
     * @return array
     */
    public function toArray($notifiable)
    {
        return [
            //
        ];
    }
}

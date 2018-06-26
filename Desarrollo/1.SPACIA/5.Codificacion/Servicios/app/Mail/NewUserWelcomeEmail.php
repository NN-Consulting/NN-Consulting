<?php

namespace App\Mail;

use App\User;
use Illuminate\Bus\Queueable;
use Illuminate\Mail\Mailable;
use Illuminate\Queue\SerializesModels;
use Illuminate\Contracts\Queue\ShouldQueue;

/**
 * Class NewUserWelcomeEmail
 *
 * @package App\Mail
 */
class NewUserWelcomeEmail extends Mailable implements ShouldQueue
{
    use Queueable, SerializesModels;

    public $subject = 'Bienvenido a Pichanga';

    /**
     * @var User
     */
    public $user;

    /**
     * Create a new message instance.
     *
     * @param User $user
     */
    public function __construct(User $user)
    {
        $this->user = $user;
    }

    /**
     * Build the message.
     *
     * @return $this
     */
    public function build()
    {
        return
            $this
            ->subject('¡Bienvenido a SPACIA!')
            ->view('emails.new_user_welcome')
            ->with(['user' => $this->user])
            ;
    }

    public function onQueue($queue)
    {
        return 'low';
    }
}

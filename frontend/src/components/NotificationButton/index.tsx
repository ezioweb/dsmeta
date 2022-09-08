import icon from '../../assets/img/notification-icon.svg';
import './styles.css';

function NotificationButton() {
  return (
    <div className="dsmeta-btn-red">
      <img src={icon} alt="notificar" />
    </div>
  );
}

export default NotificationButton;

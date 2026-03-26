import { Analytics } from "@vercel/analytics/react"
import './App.css'

function App() {
  return (
    <>
      <div className="grain" />

      <span className="corner tl">GRASPIT</span>
      <span className="corner tr">© 2025</span>
      <span className="corner bl">GRASPIT.APP</span>
      <span className="corner br">V 1.0</span>

      <div className="wrapper">
        <div className="eyebrow">Coming soon</div>

        <h1>
          Learn it.<br />
          <em>Grasp</em> it.<br />
          Keep it.
        </h1>

        <div className="rule" />

        <p className="body-text">
          GraspIt is a flashcard app built for students who actually want to
          remember what they study. Spaced repetition, minimal friction,
          maximum recall.
        </p>

        <div className="status-row">
          <div className="status-item">
            <span className="status-label">Status</span>
            <span className="status-value">
              <span className="dot" />In development
            </span>
          </div>
          <div className="divider" />
          <div className="status-item">
            <span className="status-label">Est. launch</span>
            <span className="status-value">Soon™</span>
          </div>
          <div className="divider" />
          <div className="status-item">
            <span className="status-label">Built for</span>
            <span className="status-value">Students</span>
          </div>
        </div>
      </div>

      <Analytics />
    </>
  )
}

export default App
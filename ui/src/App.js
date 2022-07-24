import './App.css';
import {
    Routes,
    Route,
} from "react-router-dom";
import Attachment from "./pages/attachment";
import AttachmentInsertOrUpdate from "./pages/attachment/insertOrUpdate";

function App() {
  return (
      <>
          <Routes>
              <Route path="/" element={<Attachment/>} />

              {["/insert", "/:id"].map((path, index) =>
                <Route
                    path={path}
                    element={
                        <AttachmentInsertOrUpdate/>
                    }
                    key={index}
                />
              )}
          </Routes>
      </>
  );
}

export default App;

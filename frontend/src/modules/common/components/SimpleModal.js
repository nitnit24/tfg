import React from 'react';

class SimpleModal extends React.Component {

render () {
  const { onCloseRequest, children} = this.props;

  return (
    <div>
      <div ref={node => (this.modal = node)}>
        <div>
          {children}
        </div>
      </div>

      <button type="button"  className="btn btn-light btn-sm"
        onClick={onCloseRequest} 
      />
    </div>
  );

}
}


export default (SimpleModal);
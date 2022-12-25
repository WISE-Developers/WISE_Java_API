package ca.wise.api.output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Strings;

import ca.wise.api.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * After a file has been written by W.I.S.E. it can be uploaded to a GeoServer
 * instance by Manager.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoServerOutputStreamInfo extends OutputStreamInfo {

	/**
	 * The username to authenticate on GeoServer with.
	 */
	@Getter @Setter
	@Builder.Default
	private String username = "";

	/**
	 * A password to authenticate on GeoServer with.
	 * WARNING: this password will be saved in plain text.
	 */
	@Getter @Setter
	@Builder.Default
	private String password = "";

	/**
	 * The URL of the GeoServer instance to upload the file to.
	 * The address of the REST API should be {@link GeoServerOutputStreamInfo#url url}/rest and the
	 * URL of the web interface should be {@link GeoServerOutputStreamInfo#url url}/web.
	 */
	@Getter @Setter
	@Builder.Default
	private String url = "";

	/**
	 * The workspace to add the file to.
	 * If the workspace doesn't exist it will be created.
	 */
	@Getter @Setter
	@Builder.Default
	private String workspace = "";

	/**
	 * The coverage store to add the file to.
	 * If the coverage store doesn't exist it will be created.
	 */
	@Getter @Setter
	@Builder.Default
	private String coverageStore = "";

	/**
	 * The declared spatial reference system for the added coverage.
	 * If this is not specified the uploaded coverage will not be
	 * enabled.
	 */
	@Getter @Setter
	@Builder.Default
	private String declaredSrs = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidationError> checkValid() {
		List<ValidationError> errs = new ArrayList<>();

		if (Strings.isNullOrEmpty(username))
			errs.add(new ValidationError("username", "No login username for the GeoServer instance was specified.", this));
		if (Strings.isNullOrEmpty(password))
			errs.add(new ValidationError("password", "No login password for the GeoServer instance was specified.", this));
		if (Strings.isNullOrEmpty(url))
			errs.add(new ValidationError("password", "No login password for the GeoServer instance was specified.", this));
		if (Strings.isNullOrEmpty(workspace))
			errs.add(new ValidationError("password", "No GeoServer workspace to store the exported files in was specified.", this));
		if (Strings.isNullOrEmpty(coverageStore))
			errs.add(new ValidationError("password", "No GeoServer coverage store inside the workspace to store the exported files in was specified.", this));
		
		return Collections.unmodifiableList(errs);
	}
}
